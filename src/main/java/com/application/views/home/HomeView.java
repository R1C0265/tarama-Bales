package com.application.views.home;

import com.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import jakarta.annotation.security.RolesAllowed;


@PageTitle("Home")
@Route(value = "", layout = MainLayout.class)
@RolesAllowed("USER")
public class HomeView extends VerticalLayout {

   /*  public HomeView() {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("This place intentionally left empty");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }


 */
public HomeView() {
    HorizontalLayout layoutRow = new HorizontalLayout();
    VerticalLayout layoutColumn2 = new VerticalLayout();
    HorizontalLayout layoutRow2 = new HorizontalLayout();
    H2 h2 = new H2();
    H2 h22 = new H2();
    HorizontalLayout layoutRow3 = new HorizontalLayout();
    FormLayout formLayout3Col = new FormLayout();
    Icon icon = new Icon();
    Icon icon2 = new Icon();
    Icon icon3 = new Icon();
    H4 h4 = new H4();
    Hr hr = new Hr();
    VerticalLayout layoutColumn3 = new VerticalLayout();
    Hr hr2 = new Hr();
    H2 h23 = new H2();
    HorizontalLayout layoutRow4 = new HorizontalLayout();
    H4 h42 = new H4();
    H4 h43 = new H4();
    H2 h24 = new H2();
    HorizontalLayout layoutRow5 = new HorizontalLayout();
    H4 h44 = new H4();
    H4 h45 = new H4();
    Hr hr3 = new Hr();
    H2 h25 = new H2();
    HorizontalLayout layoutRow6 = new HorizontalLayout();
    H4 h46 = new H4();
    H4 h47 = new H4();
    H2 h26 = new H2();
    HorizontalLayout layoutRow7 = new HorizontalLayout();
    H4 h48 = new H4();
    H4 h49 = new H4();
    getElement().setProperty("style", "width:100%");
    getElement().getStyle().set("flex-grow", "1");
    layoutRow.addClassName(Gap.MEDIUM);
    layoutRow.setWidth("100%");
    layoutRow.getStyle().set("flex-grow", "1");
    layoutColumn2.setWidth("100%");
    layoutColumn2.getStyle().set("flex-grow", "1");
    layoutRow2.setWidthFull();
    layoutColumn2.setFlexGrow(1.0, layoutRow2);
    layoutRow2.addClassName(Gap.MEDIUM);
    layoutRow2.setWidth("100%");
    layoutRow2.setHeight("80px");
    h2.setText("Welcome");
    h2.setWidth("max-content");
    h22.setText("User");
    h22.setWidth("max-content");
    layoutRow3.setWidthFull();
    layoutColumn2.setFlexGrow(1.0, layoutRow3);
    layoutRow3.addClassName(Gap.MEDIUM);
    layoutRow3.setWidth("100%");
    layoutRow3.getStyle().set("flex-grow", "1");
    formLayout3Col.setWidth("100%");
    formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
            new ResponsiveStep("500px", 3));
    icon.setIcon("lumo:user");
    icon.getStyle().set("flex-grow", "1");
    icon2.setIcon("lumo:user");
    icon3.setIcon("lumo:user");
    h4.setText("Heading");
    h4.setWidth("max-content");
    layoutColumn3.getStyle().set("flex-grow", "1");
    h23.setText("Current Bails In total");
    h23.setWidth("max-content");
    layoutRow4.setWidthFull();
    layoutColumn3.setFlexGrow(1.0, layoutRow4);
    layoutRow4.addClassName(Gap.MEDIUM);
    layoutRow4.setWidth("100%");
    layoutRow4.getStyle().set("flex-grow", "1");
    h42.setText("MWK:");
    h42.setWidth("max-content");
    h43.setText("Heading");
    h43.setWidth("max-content");
    h24.setText("Most Purchased Bail");
    h24.setWidth("max-content");
    layoutRow5.setWidthFull();
    layoutColumn3.setFlexGrow(1.0, layoutRow5);
    layoutRow5.addClassName(Gap.MEDIUM);
    layoutRow5.setWidth("100%");
    layoutRow5.getStyle().set("flex-grow", "1");
    h44.setText("MWK:");
    h44.setWidth("max-content");
    h45.setText("Heading");
    h45.setWidth("max-content");
    h25.setText("Total Bail Investment");
    h25.setWidth("max-content");
    layoutRow6.setWidthFull();
    layoutColumn3.setFlexGrow(1.0, layoutRow6);
    layoutRow6.addClassName(Gap.MEDIUM);
    layoutRow6.setWidth("100%");
    layoutRow6.getStyle().set("flex-grow", "1");
    h46.setText("MWK:");
    h46.setWidth("max-content");
    h47.setText("Heading");
    h47.setWidth("max-content");
    h26.setText("Total Bail Returns");
    h26.setWidth("max-content");
    layoutRow7.setWidthFull();
    layoutColumn3.setFlexGrow(1.0, layoutRow7);
    layoutRow7.addClassName(Gap.MEDIUM);
    layoutRow7.setWidth("100%");
    layoutRow7.getStyle().set("flex-grow", "1");
    h48.setText("MWK:");
    h48.setWidth("max-content");
    h49.setText("Heading");
    h49.setWidth("max-content");
    add(layoutRow);
    layoutRow.add(layoutColumn2);
    layoutColumn2.add(layoutRow2);
    layoutRow2.add(h2);
    layoutRow2.add(h22);
    layoutColumn2.add(layoutRow3);
    layoutRow3.add(formLayout3Col);
    formLayout3Col.add(icon);
    formLayout3Col.add(icon2);
    formLayout3Col.add(icon3);
    formLayout3Col.add(h4);
    layoutColumn2.add(hr);
    layoutRow.add(layoutColumn3);
    layoutColumn3.add(hr2);
    layoutColumn3.add(h23);
    layoutColumn3.add(layoutRow4);
    layoutRow4.add(h42);
    layoutRow4.add(h43);
    layoutColumn3.add(h24);
    layoutColumn3.add(layoutRow5);
    layoutRow5.add(h44);
    layoutRow5.add(h45);
    layoutColumn3.add(hr3);
    layoutColumn3.add(h25);
    layoutColumn3.add(layoutRow6);
    layoutRow6.add(h46);
    layoutRow6.add(h47);
    layoutColumn3.add(h26);
    layoutColumn3.add(layoutRow7);
    layoutRow7.add(h48);
    layoutRow7.add(h49);
}
}
