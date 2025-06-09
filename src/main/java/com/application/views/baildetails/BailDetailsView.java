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
import com.vaadin.flow.component.html.H6;
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
import com.vaadin.flow.component.dialog.Dialog;
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
    private final H6 editInfoH6 = new H6(
            "You can edit your files below by clicking on Edit. Take note that Deleting The Bail will Delete is Irreversible and can only be done by you as the Administrator");
    private final H6 progressLabelH6 = new H6(
            "Progress to Completion");

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
    // Removed numberField3 and numberField4 as per user request
    private final HorizontalLayout bailGradeButtonsHorizontalLayout = new HorizontalLayout();

    private final HorizontalLayout addBailGradeButtonLayout = new HorizontalLayout();
    private final VerticalLayout gradesContainer = new VerticalLayout();
    private final Button addNewGradeButton = new Button("Create Grade", e->{openGradeForm();});
     

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

        addNewGradeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Create a vertical layout for the title, info, and progress bar
        VerticalLayout titleAndProgressLayout = new VerticalLayout();
        titleAndProgressLayout.setPadding(false);
        titleAndProgressLayout.setSpacing(false);
        titleAndProgressLayout.setAlignItems(VerticalLayout.Alignment.START);
        titleAndProgressLayout.add(h3);
        titleAndProgressLayout.add(progressLabelH6);
        titleAndProgressLayout.add(progressBar);

        image.setSrc("images/empty-plant.png");
        image.setWidth("200px");
        image.setHeight("200px");

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
        // Removed numberField3, numberField4, buttonPrimary5, buttonPrimary6 and
        // related layout code as per user request

        // Add the title/progress layout and image to the main row
        mainHorizontalLayoutRow.add(titleAndProgressLayout, image);
        getContent().add(mainHorizontalLayoutRow);
        progressBar.setValue(50);
        progressBar.setWidth("40%");
        // Vaadin ProgressBar does not have a built-in label/title method, so we use H6
        // above it.

        mainHorizontalLayoutRow.add(gradesContainer);
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
        getContent().add(bailGradeButtonsHorizontalLayout);
        getContent().add(addBailGradeButtonLayout);
        getContent().add(gradesContainer);
        getContent().add(addNewGradeButton);

        this.bailGradeService = bailGradeService;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> bailId = event.getRouteParameters().get("bailID").map(Long::parseLong);
        if (bailId.isPresent()) {
            Optional<Bail> bailFromBackend = bailService.get(bailId.get());
            if (bailFromBackend.isPresent()) {
                this.bail = bailFromBackend.get();
                displayBailDetails();
                displayBailGradeDetails(); // Always call this to refresh grades
            } else {
                Notification.show("Bail not found", 3000, Notification.Position.BOTTOM_START);
                UI.getCurrent().navigate(AllBailsView.class);
            }
        }
    }

    // this should be a modal with 6 textfields and a Button. it is to add the
    // Grade.

    private void openGradeForm() {
        // Create the dialog
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Add New Grade");

        // Create 6 text fields
        TextField gradeNumberField = new TextField("Grade Number");
        TextField quantityField = new TextField("Quantity");
        TextField pricePerItemField = new TextField("Price Per Item");
        TextField descriptionField = new TextField("Description");
        TextField supplierField = new TextField("Supplier");
        TextField notesField = new TextField("Notes");

        // Arrange fields in a vertical layout
        VerticalLayout fieldsLayout = new VerticalLayout(
                gradeNumberField,
                quantityField,
                pricePerItemField,
                descriptionField,
                supplierField,
                notesField);
        fieldsLayout.setPadding(false);
        fieldsLayout.setSpacing(true);

        // Create buttons
        Button saveButton = new Button("Save", event -> {
            // TODO: Save logic here
            dialog.close();
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancelButton = new Button("Cancel", event -> dialog.close());

        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, cancelButton);

        dialog.add(fieldsLayout, buttonsLayout);
        dialog.open();
    }

    private void displayBailDetails() {
        // Setting the bail Details below

        buttonPrimary2.setEnabled(false);
        h3.setText("Bail Details for: " + bail.getBailName());
        textField.setValue(bail.getBailName());
        textField.setReadOnly(true);

        numberField.setValue((double) bail.getCurrentAmountOfItems());
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
        gradesContainer.removeAll(); // Clear previous grades

        List<BailGrade> grades = bail.getGrades();
        if (grades == null || grades.isEmpty()) {
            gradesContainer.add(new Span("No grades available for this bail."));
            return;
        }

        for (BailGrade grade : grades) {
            // Create fields for each property
            TextField gradeNumberField = new TextField("Grade Number");
            gradeNumberField.setValue(grade.getGradeNumber() != null ? grade.getGradeNumber().toString() : "");
            gradeNumberField.setReadOnly(true);

            TextField initialQuantityField = new TextField("Initial Quantity");
            initialQuantityField.setValue(grade.getInitialQuantity() != null ? grade.getInitialQuantity().toString() : "");
            initialQuantityField.setReadOnly(true);

            TextField currentQuantityField = new TextField("Current Quantity");
            currentQuantityField.setValue(grade.getCurrentQuantity() != null ? grade.getCurrentQuantity().toString() : "");
            currentQuantityField.setReadOnly(true);

            TextField pricePerItemField = new TextField("Price Per Item");
            pricePerItemField.setValue(grade.getPricePerItem() != null ? grade.getPricePerItem().toString() : "");
            pricePerItemField.setReadOnly(true);

            TextField recordedByField = new TextField("Recorded By");
            recordedByField.setValue(grade.getRecordedBy() != null ? grade.getRecordedBy() : "");
            recordedByField.setReadOnly(true);

            TextField createdDateField = new TextField("Created Date");
            createdDateField.setValue(grade.getCreatedDate() != null ? grade.getCreatedDate().toString() : "");
            createdDateField.setReadOnly(true);

            // Add more fields as needed

            // Layout for this grade
            HorizontalLayout gradeLayout = new HorizontalLayout(
                    gradeNumberField,
                    initialQuantityField,
                    currentQuantityField,
                    pricePerItemField,
                    recordedByField,
                    createdDateField);
            gradeLayout.setWidthFull();

            // Optionally, add buttons for each grade (edit/delete)
            Button editButton = new Button("Edit");
            Button deleteButton = new Button("Delete");
            // Add listeners as needed

            HorizontalLayout buttonsLayout = new HorizontalLayout(editButton, deleteButton);

            VerticalLayout gradeRow = new VerticalLayout(gradeLayout, buttonsLayout);
            gradeRow.setPadding(false);
            gradeRow.setSpacing(false);

            gradesContainer.add(gradeRow);

        }


    }

    private void enableBailEditing() {
        textField.setReadOnly(false);
        numberField.setReadOnly(false);
        numberField2.setReadOnly(false);
        datePicker.setReadOnly(false);
        buttonPrimary.setEnabled(false);
        buttonPrimary2.setEnabled(true);
        Notification.show("Edit mode enabled", 2000, Notification.Position.BOTTOM_START);
    }

    private void saveBail() {
        if (bail != null) {
            bail.setBailName(textField.getValue());
            bail.setCurrentAmountOfItems(numberField.getValue().intValue());
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
