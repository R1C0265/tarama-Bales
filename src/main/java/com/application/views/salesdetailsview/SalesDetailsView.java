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
import com.vaadin.flow.router.Menu;
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
public class SalesDetailsView extends Composite<VerticalLayout> implements BeforeEnterObserver{

    private final PurchaseService purchaseService;
    private Purchase purchase;

    public SalesDetailsView(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;

        HorizontalLayout layoutRow = new HorizontalLayout();
        H3 h3 = new H3();
        Button buttonPrimary = new Button();
        Hr hr = new Hr();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Icon icon = new Icon();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        DatePicker datePicker = new DatePicker();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        ComboBox comboBox = new ComboBox();
        ComboBox comboBox2 = new ComboBox();
        NumberField numberField = new NumberField();
        HorizontalLayout layoutRow5 = new HorizontalLayout();
        Button buttonPrimary2 = new Button();
        Button buttonPrimary3 = new Button();
        Button buttonPrimary4 = new Button();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        H4 h4 = new H4();
        H6 h6 = new H6();
        H6 h62 = new H6();
        H6 h63 = new H6();
        H6 h64 = new H6();
        H6 h65 = new H6();
        H6 h66 = new H6();
        H6 h67 = new H6();
        Button buttonPrimary5 = new Button();
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
                // displayDetails();
            } else {
                Notification.show("Purchase not found", 3000, Notification.Position.BOTTOM_START);
                UI.getCurrent().navigate(SalesView.class);
            }
        }
    }


    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
