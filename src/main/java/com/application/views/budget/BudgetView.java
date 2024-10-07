package com.application.views.budget;

import com.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Budget")
@Route(value = "budget", layout = MainLayout.class)
@AnonymousAllowed
public class BudgetView extends Composite<VerticalLayout> {

    public BudgetView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        TextField textField = new TextField();
        DatePicker datePicker = new DatePicker();
        NumberField numberField = new NumberField();
        H3 h32 = new H3();
        H4 h4 = new H4();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutColumn2.getStyle().set("flex-grow", "1");
        h3.setText("Expenses");
        h3.setWidth("max-content");
        textField.setLabel("Category");
        textField.setWidth("min-content");
        datePicker.setLabel("Date");
        datePicker.setWidth("min-content");
        numberField.setLabel("Amount: MWK");
        numberField.setWidth("min-content");
        h32.setText("Total Expenses");
        h32.setWidth("max-content");
        h4.setText("MWK:");
        h4.setWidth("max-content");
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        layoutColumn4.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth("100%");
        layoutColumn4.getStyle().set("flex-grow", "1");
        getContent().add(layoutRow);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(textField);
        layoutColumn2.add(datePicker);
        layoutColumn2.add(numberField);
        layoutColumn2.add(h32);
        layoutColumn2.add(h4);
        layoutRow.add(layoutColumn3);
        layoutColumn3.add(layoutColumn4);
    }
}
