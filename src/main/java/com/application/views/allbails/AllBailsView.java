package com.application.views.allbails;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.application.data.Bail;
import com.application.services.BailService;
import com.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("All Bails")
@Route(value = "all-bails/:bailID?/:action?(edit)", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class AllBailsView extends Div implements BeforeEnterObserver {

    private final String BAIL_ID = "bailID";
    private final String BAIL_EDIT_ROUTE_TEMPLATE = "all-bails/%s/edit";

    private final Grid<Bail> grid = new Grid<>(Bail.class, false);

    private TextField bailName;
    private TextField amounOfItems;
    private TextField bailPrice;
    private DatePicker dateOfPurchase;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");

    private final BeanValidationBinder<Bail> binder;

    private Bail bail;

    private final BailService bailService;

    public AllBailsView(BailService bailService) {
        this.bailService = bailService;
        addClassNames("all-bails-view");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("bailName").setAutoWidth(true);
        grid.addColumn("amounOfItems").setAutoWidth(true);
        grid.addColumn("bailPrice").setAutoWidth(true);
        grid.addColumn("dateOfPurchase").setAutoWidth(true);
        grid.setItems(query -> bailService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(BAIL_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(AllBailsView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Bail.class);

        // Bind fields. This is where you'd define e.g. validation rules
        binder.forField(amounOfItems).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("amounOfItems");
        binder.forField(bailPrice).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("bailPrice");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });


        delete.addClickListener(e->{
            delete();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.bail == null) {
                    this.bail = new Bail();
                }
                binder.writeBean(this.bail);
                bailService.update(this.bail);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(AllBailsView.class);
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
        Optional<Long> bailId = event.getRouteParameters().get(BAIL_ID).map(Long::parseLong);
        if (bailId.isPresent()) {
            Optional<Bail> bailFromBackend = bailService.get(bailId.get());
            if (bailFromBackend.isPresent()) {
                populateForm(bailFromBackend.get());
            } else {
                Notification.show(String.format("The requested bail was not found, ID = %s", bailId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(AllBailsView.class);
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
        bailPrice = new TextField("Bail Price");
        dateOfPurchase = new DatePicker("Date Of Purchase");
        formLayout.add(bailName, amounOfItems, bailPrice, dateOfPurchase);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonLayout.add(save, cancel, delete);
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

    private void delete() {
        bailService.delete(bail.getId());
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Bail value) {
        this.bail = value;
        binder.readBean(this.bail);

    }
}
