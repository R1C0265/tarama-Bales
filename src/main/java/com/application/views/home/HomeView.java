package com.application.views.home;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.data.Updates;
import com.application.services.UpdatesService;
import com.application.views.MainLayout;
import com.application.components.avataritem.AvatarItem;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Home")
@Route(value = "", layout = MainLayout.class)
@RolesAllowed("USER")

public class HomeView extends Composite<VerticalLayout> {

    /*public HomeView() {
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
        Span badge = new Span();
        H6 h6 = new H6();
        Span badge2 = new Span();
        H6 h62 = new H6();
        Span badge3 = new Span();
        H6 h63 = new H6();
        Span badge4 = new Span();
        ProgressBar progressBar = new ProgressBar();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        H4 h4 = new H4();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        AvatarItem avatarItem = new AvatarItem();
        AvatarItem avatarItem2 = new AvatarItem();
        AvatarItem avatarItem3 = new AvatarItem();
        AvatarItem avatarItem4 = new AvatarItem();
        Hr hr = new Hr();
        H5 h5 = new H5();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        Grid minimalistGrid = new Grid(Updates.class);
        DatePicker datePicker = new DatePicker();
        getContent().setWidth("max-content");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutColumn2.getStyle().set("flex-grow", "1");
        badge.setText("CUSTOMERS DEBTS");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, badge);
        badge.setWidth("100%");
        badge.getElement().getThemeList().add("badge");
        h6.setText("12");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, h6);
        h6.setWidth("max-content");
        badge2.setText("TOTAL SALES (06/25)");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, badge2);
        badge2.setWidth("100%");
        badge2.getElement().getThemeList().add("badge");
        h62.setText("200");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, h62);
        h62.setWidth("max-content");
        badge3.setText("TOTAL SALES (06/25)");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, badge3);
        badge3.setWidth("100%");
        badge3.getElement().getThemeList().add("badge");
        h63.setText("200");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, h63);
        h63.setWidth("max-content");
        badge4.setText("BREAK EVEN POINT");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, badge4);
        badge4.setWidth("100%");
        badge4.getElement().getThemeList().add("badge");
        progressBar.setValue(0.5);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        h4.setText("WELCOME USER");
        layoutColumn3.setAlignSelf(FlexComponent.Alignment.START, h4);
        h4.setWidth("max-content");
        layoutRow2.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.setHeight("min-content");
        avatarItem.setWidth("min-content");
        setAvatarItemSampleData(avatarItem);
        avatarItem2.setWidth("min-content");
        setAvatarItemSampleData(avatarItem2);
        avatarItem3.setWidth("min-content");
        setAvatarItemSampleData(avatarItem3);
        avatarItem4.setWidth("min-content");
        setAvatarItemSampleData(avatarItem4);
        h5.setText("NOTIFICATIONS");
        h5.setWidth("max-content");
        layoutRow3.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        minimalistGrid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS);
        minimalistGrid.setWidth("100%");
        minimalistGrid.setHeight("100%");
        setGridSampleData(minimalistGrid);
        datePicker.setHeight("500px");
        datePicker.setWidth("max-content");
        getContent().add(layoutRow);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(badge);
        layoutColumn2.add(h6);
        layoutColumn2.add(badge2);
        layoutColumn2.add(h62);
        layoutColumn2.add(badge3);
        layoutColumn2.add(h63);
        layoutColumn2.add(badge4);
        layoutColumn2.add(progressBar);
        layoutRow.add(layoutColumn3);
        layoutColumn3.add(h4);
        layoutColumn3.add(layoutRow2);
        layoutRow2.add(avatarItem);
        layoutRow2.add(avatarItem2);
        layoutRow2.add(avatarItem3);
        layoutRow2.add(avatarItem4);
        layoutColumn3.add(hr);
        layoutColumn3.add(h5);
        layoutColumn3.add(layoutRow3);
        layoutRow3.add(minimalistGrid);
        layoutRow3.add(datePicker);
    }

    private void setAvatarItemSampleData(AvatarItem avatarItem) {
        avatarItem.setHeading("Aria Bailey");
        avatarItem.setDescription("Endocrinologist");
        avatarItem.setAvatar(new Avatar("Aria Bailey"));
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> updatesService.list(VaadinSpringDataHelpers.toSpringPageRequest(query)).stream());
    }

    @Autowired()
    private UpdatesService updatesService;

}