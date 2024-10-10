package com.application.views.sales;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.application.data.Purchase;
import com.application.services.PurchaseService;
import com.application.views.MainLayout;
import com.vaadin.collaborationengine.CollaborationAvatarGroup;
import com.vaadin.collaborationengine.CollaborationBinder;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import jakarta.annotation.security.PermitAll;

@PageTitle("Sales")
@Route(value = "sales/:purchaseID?/:action?(edit)", layout = MainLayout.class)
@PermitAll
public class SalesView extends Div implements BeforeEnterObserver {

    private final String PURCHASE_ID = "purchaseID";
    private final String PURCHASE_EDIT_ROUTE_TEMPLATE = "sales/%s/edit";

    private final Grid<Purchase> grid = new Grid<>(Purchase.class, false);

    CollaborationAvatarGroup avatarGroup;

    private TextField bailName;
    private TextField amounOfItems;
    private ComboBox<String> bailCategory;
    private TextField price;
    private DatePicker purchaseDate;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private final CollaborationBinder<Purchase> binder;

    private Purchase purchase;

    private final PurchaseService purchaseService;

    public SalesView(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
        addClassNames("sales-view");

        // UserInfo is used by Collaboration Engine and is used to share details
        // of users to each other to able collaboration. Replace this with
        // information about the actual user that is logged, providing a user
        // identifier, and the user's real name. You can also provide the users
        // avatar by passing an url to the image as a third parameter, or by
        // configuring an `ImageProvider` to `avatarGroup`.
        UserInfo userInfo = new UserInfo(UUID.randomUUID().toString(), "Steve Lange");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        avatarGroup = new CollaborationAvatarGroup(userInfo, null);
        avatarGroup.getStyle().set("visibility", "hidden");

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("bailName").setAutoWidth(true);
        grid.addColumn("amounOfItems").setAutoWidth(true);
        grid.addColumn("price").setAutoWidth(true);
        grid.addColumn("purchaseDate").setAutoWidth(true);
        grid.setItems(query -> purchaseService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(PURCHASE_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(SalesView.class);
            }
        });

        // Configure Form
        binder = new CollaborationBinder<>(Purchase.class, userInfo);

        // Bind fields. This is where you'd define e.g. validation rules
        binder.forField(amounOfItems, String.class)
                .withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("amounOfItems");
        binder.forField(price, String.class).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("price");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.purchase == null) {
                    this.purchase = new Purchase();
                }
                binder.writeBean(this.purchase);
                purchaseService.update(this.purchase);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(SalesView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } catch (ValidationException validationException) {
                Notification.show("Failed to update the data. Check again that all values are valid");
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> purchaseId = event.getRouteParameters().get(PURCHASE_ID).map(Long::parseLong);
        if (purchaseId.isPresent()) {
            Optional<Purchase> purchaseFromBackend = purchaseService.get(purchaseId.get());
            if (purchaseFromBackend.isPresent()) {
                populateForm(purchaseFromBackend.get());
            } else {
                Notification.show(String.format("The requested purchase was not found, ID = %d", purchaseId.get()),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(SalesView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        bailName = new TextField("Bail Name");
        amounOfItems = new TextField("Amoun Of Items");
        bailCategory = new ComboBox<>("Select Option");
        bailCategory.setItems("Dresses", "Kid's Dresses", "Boy's Cargo Pants");

        // Step 3: Optional - Add a placeholder or value change listener
        bailCategory.setPlaceholder("Choose an option...");

        bailCategory.addValueChangeListener(event -> {
            String selected = event.getValue();
            // Handle the value change
            System.out.println("Selected: " + selected);
        });
        price = new TextField("Price");
        purchaseDate = new DatePicker("Purchase Date");
        formLayout.add(bailName, bailCategory, amounOfItems, price, purchaseDate);

        editorDiv.add(avatarGroup, formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Purchase value) {
        this.purchase = value;
        String topic = null;
        if (this.purchase != null && this.purchase.getId() != null) {
            topic = "purchase/" + this.purchase.getId();
            avatarGroup.getStyle().set("visibility", "visible");
        } else {
            avatarGroup.getStyle().set("visibility", "hidden");
        }
        binder.setTopic(topic, () -> this.purchase);
        avatarGroup.setTopic(topic);

    }
}
