package com.application.views.baildetails;

import com.application.data.Bail;
import com.application.data.BailGrade;
import com.application.services.BailService;
import com.application.services.BailGradeService;

import com.application.views.MainLayout;
import com.application.views.allbails.AllBailsView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.UI;

import java.util.List;
import java.util.Optional;
import jakarta.annotation.security.PermitAll;

@PageTitle("Bail Details")
@Route(value = "bail-details/:bailID", layout = MainLayout.class)
@PermitAll

@Menu(title = "Bail Details", icon = "user", order = 1)

public class BailDetailsView extends Composite<VerticalLayout> implements BeforeEnterObserver {

    private final BailService bailService;
    private final BailGradeService bailGradeService;
    private Bail bail;
    private BailGrade bailGrade;
    private final HorizontalLayout mainHorizontalLayoutRow = new HorizontalLayout();
    private final H3 h3 = new H3();
    private final Image image = new Image();
    private final ProgressBar progressBar = new ProgressBar(0, 100);
    private final HorizontalLayout bailDetailsHorizontalLayout = new HorizontalLayout();
    private final TextField textField = new TextField();
    private final DatePicker datePicker = new DatePicker();
    private final NumberField numberField = new NumberField();
    private final NumberField numberField2 = new NumberField();
    private final HorizontalLayout bailDetailsButtonsLayout = new HorizontalLayout();
    private final Button buttonPrimary = new Button();
    private final Button buttonPrimary2 = new Button();
    private final Button buttonPrimary3 = new Button();
    private final Hr hr = new Hr();
    private final H5 h5 = new H5();
    private final HorizontalLayout bailGradeHorizontalLayout = new HorizontalLayout();
    private final HorizontalLayout bailGradeDetailsHorizontalLayout = new HorizontalLayout();
    private final Span badge = new Span();
    private final NumberField numberField3 = new NumberField();
    private final NumberField numberField4 = new NumberField();
    private final HorizontalLayout bailGradeButtonsHorizontalLayout = new HorizontalLayout();
    private final Button buttonPrimary4 = new Button();
    private final Button buttonPrimary5 = new Button();
    private final Button buttonPrimary6 = new Button();
    private final HorizontalLayout addBailGradeButtonLayout = new HorizontalLayout();
    private final Button addBailGradeButton = new Button("ADD A GRADE");

    public BailDetailsView(BailService bailService, BailGradeService bailGradeService) {
        this.bailService = bailService;

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        mainHorizontalLayoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, mainHorizontalLayoutRow);
        mainHorizontalLayoutRow.addClassName(Gap.MEDIUM);
        mainHorizontalLayoutRow.setWidth("100%");
        mainHorizontalLayoutRow.setHeight("flex-grow");
        h3.setText("Bail Details");
        h3.getStyle().set("flex-grow", "1");

        image.setSrc("images/empty-plant.png");
        image.setWidth("150px");
        image.setHeight("150px");

        bailDetailsHorizontalLayout.setWidthFull();
        getContent().setFlexGrow(1.0, bailDetailsHorizontalLayout);
        bailDetailsHorizontalLayout.addClassName(Gap.MEDIUM);
        bailDetailsHorizontalLayout.setWidth("100%");
        bailDetailsHorizontalLayout.setHeight("flex-grow");
        textField.setLabel("Bail Name");
        textField.getStyle().set("flex-grow", "1");
        datePicker.setLabel("Purchased On");
        datePicker.getStyle().set("flex-grow", "1");
        numberField.setLabel("Number of Clothes");
        numberField.getStyle().set("flex-grow", "1");
        numberField2.setLabel("Number field");
        numberField2.getStyle().set("flex-grow", "1");
        bailDetailsButtonsLayout.setWidthFull();
        getContent().setFlexGrow(1.0, bailDetailsButtonsLayout);
        bailDetailsButtonsLayout.addClassName(Gap.MEDIUM);
        bailDetailsButtonsLayout.setWidth("100%");
        bailDetailsButtonsLayout.setHeight("flex-grow");
        buttonPrimary.setText("EDIT BAIL");
        buttonPrimary.getStyle().set("flex-grow", "1");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary2.setText("SAVE BAIL");
        buttonPrimary2.getStyle().set("flex-grow", "1");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        buttonPrimary3.setText("DELETE BAIL");
        buttonPrimary3.getStyle().set("flex-grow", "1");
        buttonPrimary3.addThemeVariants(ButtonVariant.LUMO_ERROR);
        h5.setText("Grade Details");
        h5.setWidth("flex-grow");
        bailGradeHorizontalLayout.setWidthFull();
        getContent().setFlexGrow(1.0, bailGradeHorizontalLayout);
        bailGradeHorizontalLayout.addClassName(Gap.MEDIUM);
        bailGradeHorizontalLayout.setWidth("100%");
        bailGradeHorizontalLayout.getStyle().set("flex-grow", "1");
        bailGradeDetailsHorizontalLayout.setHeightFull();
        bailGradeHorizontalLayout.setFlexGrow(1.0, bailGradeDetailsHorizontalLayout);
        bailGradeDetailsHorizontalLayout.addClassName(Gap.MEDIUM);
        bailGradeDetailsHorizontalLayout.getStyle().set("flex-grow", "1");
        bailGradeDetailsHorizontalLayout.setHeight("flex-grow");
        badge.getStyle().set("flex-grow", "1");
        badge.setHeight("flex-grow");
        badge.getElement().getThemeList().add("badge");
        numberField3.setLabel("Number of Clothes");
        numberField3.getStyle().set("flex-grow", "1");
        numberField4.setLabel("Price Per Cloth");
        numberField4.getStyle().set("flex-grow", "1");
        bailGradeButtonsHorizontalLayout.setWidthFull();
        getContent().setFlexGrow(1.0, bailGradeButtonsHorizontalLayout);
        bailGradeButtonsHorizontalLayout.addClassName(Gap.MEDIUM);
        bailGradeButtonsHorizontalLayout.setWidth("100%");
        bailGradeButtonsHorizontalLayout.setHeight("flex-grow");
        buttonPrimary4.getStyle().set("flex-grow", "1");
        buttonPrimary4.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonPrimary5.setText("SAVE GRADE");
        buttonPrimary5.getStyle().set("flex-grow", "1");
        buttonPrimary5.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        buttonPrimary6.setText("DELETE GRADE");
        buttonPrimary6.getStyle().set("flex-grow", "1");
        buttonPrimary6.addThemeVariants(ButtonVariant.LUMO_ERROR);
        getContent().add(mainHorizontalLayoutRow);
        progressBar.setValue(50);
        mainHorizontalLayoutRow.add(h3);
        mainHorizontalLayoutRow.add(progressBar);

        mainHorizontalLayoutRow.add(image);
        getContent().add(bailDetailsHorizontalLayout);
        bailDetailsHorizontalLayout.add(textField);
        bailDetailsHorizontalLayout.add(datePicker);
        bailDetailsHorizontalLayout.add(numberField);
        bailDetailsHorizontalLayout.add(numberField2);
        getContent().add(bailDetailsButtonsLayout);
        bailDetailsButtonsLayout.add(buttonPrimary);
        bailDetailsButtonsLayout.add(buttonPrimary2);
        bailDetailsButtonsLayout.add(buttonPrimary3);
        getContent().add(hr);
        getContent().add(h5);
        getContent().add(bailGradeHorizontalLayout);
        bailGradeHorizontalLayout.add(bailGradeDetailsHorizontalLayout);
        bailGradeDetailsHorizontalLayout.add(badge);
        bailGradeDetailsHorizontalLayout.add(numberField3);
        bailGradeDetailsHorizontalLayout.add(numberField4);
        getContent().add(bailGradeButtonsHorizontalLayout);
        bailGradeButtonsHorizontalLayout.add(buttonPrimary4);
        bailGradeButtonsHorizontalLayout.add(buttonPrimary5);
        bailGradeButtonsHorizontalLayout.add(buttonPrimary6);
        addBailGradeButtonLayout.add(addBailGradeButton);
        getContent().add(addBailGradeButtonLayout);

        this.bailGradeService = bailGradeService;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> bailId = event.getRouteParameters().get("bailID").map(Long::parseLong);
        if (bailId.isPresent()) {
            Optional<Bail> bailFromBackend = bailService.get(bailId.get());
            // check if Bail exists Are Found
            if (bailFromBackend.isPresent()) {
                this.bail = bailFromBackend.get();
                // Call displayDetails after loading the bail data
                displayBailDetails();
                // check if bail grades are available
                List<BailGrade> gradeFromBackend = bailGradeService.listAllBails();
                if ((gradeFromBackend.isEmpty())) {
                    addBailGradeButtonLayout.setVisible(false);
                    displayBailGradeDetails();
                } else {
                    bailGradeDetailsHorizontalLayout.setVisible(false);
                    bailGradeButtonsHorizontalLayout.setVisible(false);
                    addBailGradeButtonLayout.setVisible(true);
                }

            } else {
                Notification.show("Bail not found", 3000, Notification.Position.BOTTOM_START);
                UI.getCurrent().navigate(AllBailsView.class);
            }
        }
    }

    // this should be a modal with 6 textfields and a Button. it is to add the
    // Grade.

    private void openGradeForm() {

    }

    private void displayBailDetails() {
        // Use the existing UI components instead of creating new ones

        // Setting the bail Details below
        h3.setText("Bail Details: " + bail.getBailName());
        textField.setValue(bail.getBailName());
        textField.setReadOnly(true);

        numberField.setValue((double) bail.getAmounOfItems());
        numberField.setReadOnly(true);

        numberField2.setLabel("Bail Price");
        numberField2.setValue(Double.valueOf(bail.getBailPrice()));
        numberField2.setReadOnly(true);

        datePicker.setValue(bail.getDateOfPurchase());
        datePicker.setReadOnly(true);

        // Set up button actions for Bails
        buttonPrimary.addClickListener(e -> enableBailEditing());
        buttonPrimary2.addClickListener(e -> saveBail());
        buttonPrimary3.addClickListener(e -> deleteBail());

        // setting the Bail Grade details
    }

    private void displayBailGradeDetails() {
        for (BailGrade grade : bailGradeService.listAllBails()) {
            badge.setText("GRADE " + grade.getGradeNumber());
            break;
        }

    }

    private void enableBailEditing() {
        textField.setReadOnly(false);
        numberField.setReadOnly(false);
        numberField2.setReadOnly(false);
        datePicker.setReadOnly(false);
        buttonPrimary.setEnabled(false);
        Notification.show("Edit mode enabled", 2000, Notification.Position.BOTTOM_START);
    }

    private void saveBail() {
        if (bail != null) {
            bail.setBailName(textField.getValue());
            bail.setAmounOfItems(numberField.getValue().intValue());
            bail.setBailPrice(numberField2.getValue().intValue());
            bail.setDateOfPurchase(datePicker.getValue());

            bailService.update(bail);
            Notification.show("Bail saved successfully", 2000, Notification.Position.BOTTOM_START);

            // Reset read-only state
            textField.setReadOnly(true);
            numberField.setReadOnly(true);
            numberField2.setReadOnly(true);
            datePicker.setReadOnly(true);
        }
    }

    private void deleteBail() {
        if (bail != null) {
            bailService.delete(bail.getId());
            Notification.show("Bail deleted", 2000, Notification.Position.BOTTOM_START);
            UI.getCurrent().navigate(AllBailsView.class);
        }
    }

}
