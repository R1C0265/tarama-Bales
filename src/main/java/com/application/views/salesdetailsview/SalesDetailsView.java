package com.application.views.salesdetailsview;

import com.application.data.Purchase;
import com.application.services.PurchaseService;
import com.application.views.sales.SalesView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.application.views.MainLayout;

@PageTitle("Purchase Details")
@Route(value = "sales-details/:purchaseID", layout = MainLayout.class)
@PermitAll
public class SalesDetailsView extends Composite<VerticalLayout> implements BeforeEnterObserver {

    private final PurchaseService purchaseService;
    private Purchase purchase;

    private final HorizontalLayout layoutRow = new HorizontalLayout();
    private final H3 h3 = new H3();
    private final Button buttonPrimary = new Button();
    private final Hr hr = new Hr();
    private final HorizontalLayout layoutRow2 = new HorizontalLayout();
    private final VerticalLayout layoutColumn2 = new VerticalLayout();
    private final Icon icon = new Icon();
    private final VerticalLayout layoutColumn3 = new VerticalLayout();
    private final HorizontalLayout layoutRow3 = new HorizontalLayout();
    private final TextField textField = new TextField();
    private final TextField textField2 = new TextField();
    private final DatePicker datePicker = new DatePicker();
    private final HorizontalLayout layoutRow4 = new HorizontalLayout();
    private final ComboBox<SampleItem> comboBox = new ComboBox<>();
    private final ComboBox<SampleItem> comboBox2 = new ComboBox<>();
    private final NumberField numberField = new NumberField();
    private final HorizontalLayout layoutRow5 = new HorizontalLayout();
    private final Button buttonPrimary2 = new Button();
    private final Button buttonPrimary3 = new Button();
    private final Button buttonPrimary4 = new Button();
    private final VerticalLayout layoutColumn4 = new VerticalLayout();
    private final H4 h4 = new H4();
    private final H6 h6 = new H6();
    private final H6 h62 = new H6();
    private final H6 h63 = new H6();
    private final H6 h64 = new H6();
    private final H6 h65 = new H6();
    private final H6 h66 = new H6();
    private final H6 h67 = new H6();
    private final Button buttonPrimary5 = new Button();

    public SalesDetailsView(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
        setLayout();

    }

    private void setLayout() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        h3.setText("Purchase Details");
        h3.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Print Reciept");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        layoutColumn2.getStyle().set("flex-grow", "1");
        icon.setIcon("lumo:user");
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        layoutRow3.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.setHeight("min-content");
        textField.setLabel("Customer Name");
        textField.setWidth("min-content");
        textField2.setLabel("Cashier Name");
        textField2.setWidth("min-content");
        datePicker.setLabel("Purchase Date");
        datePicker.setWidth("min-content");
        layoutRow4.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow4);
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.setHeight("min-content");
        comboBox.setLabel("Bail Category");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        comboBox2.setLabel("Grade");
        comboBox2.setWidth("min-content");
        setComboBoxSampleData(comboBox2);
        numberField.setLabel("Amount of Items");
        numberField.setWidth("min-content");
        layoutRow5.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow5);
        layoutRow5.addClassName(Gap.MEDIUM);
        layoutRow5.setWidth("100%");
        layoutRow5.setHeight("min-content");
        buttonPrimary2.setText("Edit Details");
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary3.setText("Save Details");
        buttonPrimary3.setWidth("min-content");
        buttonPrimary3.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary4.setText("Delete Purchase");
        buttonPrimary4.setWidth("min-content");
        buttonPrimary4.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutColumn4.getStyle().set("flex-grow", "1");
        h4.setText("Reciept Details");
        h4.setWidth("max-content");
        h6.setText("pURCHASED bY:");
        h6.setWidth("max-content");
        h62.setText("Bail:");
        h62.setWidth("max-content");
        h63.setText("Grade:");
        h63.setWidth("max-content");
        h64.setText("Price:");
        h64.setWidth("max-content");
        h65.setText("Amount Paid:");
        h65.setWidth("max-content");
        h66.setText("Sold By:");
        h66.setWidth("max-content");
        h67.setText("Date:");
        h67.setWidth("max-content");
        buttonPrimary5.setText("Print Receipt");
        buttonPrimary5.setWidth("min-content");
        buttonPrimary5.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutRow);
        layoutRow.add(h3);
        layoutRow.add(buttonPrimary);
        getContent().add(hr);
        getContent().add(layoutRow2);
        layoutRow2.add(layoutColumn2);
        layoutColumn2.add(icon);
        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(layoutRow3);
        layoutRow3.add(textField);
        layoutRow3.add(textField2);
        layoutRow3.add(datePicker);
        layoutColumn3.add(layoutRow4);
        layoutRow4.add(comboBox);
        layoutRow4.add(comboBox2);
        layoutRow4.add(numberField);
        layoutColumn3.add(layoutRow5);
        layoutRow5.add(buttonPrimary2);
        layoutRow5.add(buttonPrimary3);
        layoutRow5.add(buttonPrimary4);
        layoutRow2.add(layoutColumn4);
        layoutColumn4.add(h4);
        layoutColumn4.add(h6);
        layoutColumn4.add(h62);
        layoutColumn4.add(h63);
        layoutColumn4.add(h64);
        layoutColumn4.add(h65);
        layoutColumn4.add(h66);
        layoutColumn4.add(h67);
        layoutColumn4.add(buttonPrimary5);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> purchaseId = event.getRouteParameters().get("purchaseID").map(Long::parseLong);
        if (purchaseId.isPresent()) {
            Optional<Purchase> purchaseFromBackend = purchaseService.get(purchaseId.get());
            if (purchaseFromBackend.isPresent()) {
                this.purchase = purchaseFromBackend.get();
                displayDetails(); // Uncommented to display purchase details
            } else {
                Notification.show("Purchase not found", 3000, Notification.Position.BOTTOM_START);
                UI.getCurrent().navigate(SalesView.class);
            }
        }
    }

    /**
     * Displays the purchase details in the UI components
     */
    private void displayDetails() {
        if (purchase == null) {
            return;
        }

        // Set values in the form fields
        textField.setValue(purchase.getCustomerName() != null ? purchase.getCustomerName() : "");
        textField.setReadOnly(true);

        textField2.setValue(purchase.getCashier() != null ? purchase.getCashier() : "");
        textField2.setReadOnly(true);

        datePicker.setValue(purchase.getPurchaseDate());
        datePicker.setReadOnly(true);

        // Set bail category and grade
        if (purchase.getBailName() != null) {
            comboBox.setValue(getBailCategoryFromName(purchase.getBailName()));
            comboBox.setReadOnly(true);

            // For demo purposes, we'll set a default grade
            comboBox2.setValue(new SampleItem("first", "Grade 1", null));
            comboBox2.setReadOnly(true);
        }

        numberField.setValue((double) purchase.getAmounOfItems());
        numberField.setReadOnly(true);

        // Update receipt details
        h6.setText("Purchased By: " + (purchase.getCustomerName() != null ? purchase.getCustomerName() : "N/A"));
        h62.setText("Bail: " + (purchase.getBailName() != null ? purchase.getBailName() : "N/A"));
        h63.setText("Grade: Grade 1"); // Default grade for demo
        h64.setText("Price: MWK " + purchase.getPrice());
        h65.setText("Amount Paid: MWK " + purchase.getPrice());
        h66.setText("Sold By: " + (purchase.getCashier() != null ? purchase.getCashier() : "N/A"));
        h67.setText("Date: " + (purchase.getPurchaseDate() != null ? purchase.getPurchaseDate().toString() : "N/A"));

        // Set up button actions
        buttonPrimary.addClickListener(e -> printReceipt());
        buttonPrimary2.addClickListener(e -> enableEditing());
        buttonPrimary3.addClickListener(e -> savePurchase());
        buttonPrimary4.addClickListener(e -> deletePurchase());
        buttonPrimary5.addClickListener(e -> printReceipt());
    }

    /**
     * Enables editing of the purchase details
     */
    private void enableEditing() {
        textField.setReadOnly(false);
        textField2.setReadOnly(false);
        datePicker.setReadOnly(false);
        comboBox.setReadOnly(false);
        comboBox2.setReadOnly(false);
        numberField.setReadOnly(false);

        Notification.show("Edit mode enabled", 2000, Notification.Position.BOTTOM_START);
    }

    /**
     * Saves the purchase details
     */
    private void savePurchase() {
        if (purchase != null) {
            purchase.setCustomerName(textField.getValue());
            purchase.setCashier(textField2.getValue());
            purchase.setPurchaseDate(datePicker.getValue());

            // Get the bail name from the selected category
            if (comboBox.getValue() != null) {
                purchase.setBailName(comboBox.getValue().value());
            }

            purchase.setAmounOfItems(numberField.getValue().intValue());

            purchaseService.update(purchase);

            // Reset read-only state
            textField.setReadOnly(true);
            textField2.setReadOnly(true);
            datePicker.setReadOnly(true);
            comboBox.setReadOnly(true);
            comboBox2.setReadOnly(true);
            numberField.setReadOnly(true);

            // Update receipt details
            displayDetails();

            Notification.show("Purchase saved successfully", 2000, Notification.Position.BOTTOM_START);
        }
    }

    /**
     * Deletes the purchase
     */
    private void deletePurchase() {
        if (purchase != null) {
            purchaseService.delete(purchase.getId());
            Notification.show("Purchase deleted", 2000, Notification.Position.BOTTOM_START);
            UI.getCurrent().navigate(SalesView.class);
        }
    }

    /**
     * Prints the receipt (demo functionality)
     */
    private void printReceipt() {
        Notification.show("Printing receipt...", 2000, Notification.Position.BOTTOM_START);
        // In a real application, this would trigger the printing functionality
    }

    /**
     * Gets the bail category from the bail name
     */
    private SampleItem getBailCategoryFromName(String bailName) {
        // In a real application, this would look up the category from the database
        // For demo purposes, we'll return a default category
        return new SampleItem("first", "First", null);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox<SampleItem> comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(SampleItem::label);
    }
}
