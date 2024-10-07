package com.application.views.takeaways;

import com.application.components.pricefield.PriceField;
import com.application.data.SamplePerson;
import com.application.services.SamplePersonService;
import com.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jakarta.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Takeaways")
@Route(value = "takeaways", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class TakeawaysView extends Composite<VerticalLayout> {

    public TakeawaysView() {
        TabSheet tabSheet = new TabSheet();
        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        H1 h1 = new H1();
        ComboBox comboBox = new ComboBox();
        Button buttonPrimary = new Button();
        ProgressBar progressBar = new ProgressBar();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        TabSheet tabSheet2 = new TabSheet();
        Anchor link = new Anchor();
        RouterLink routerLink = new RouterLink();
        Grid stripedGrid = new Grid(SamplePerson.class);
        VerticalLayout layoutColumn4 = new VerticalLayout();
        H3 h32 = new H3();
        TextField textField = new TextField();
        NumberField numberField = new NumberField();
        PriceField price = new PriceField();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        tabSheet.setWidth("100%");
        setTabSheetSampleData(tabSheet);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutColumn2.getStyle().set("flex-grow", "1");
        h3.setText("Heading");
        h3.setWidth("max-content");
        h1.setText("Heading");
        h1.setWidth("max-content");
        comboBox.setLabel("Bail Category");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        buttonPrimary.setText("add Expense");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        progressBar.setValue(0.5);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        tabSheet2.setWidth("100%");
        setTabSheetSampleData(tabSheet2);
        link.setText("Hello Vaadin");
        link.setHref("#");
        link.setWidth("min-content");
        routerLink.setText("Custom View");
        routerLink.setRoute(TakeawaysView.class);
        stripedGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        stripedGrid.setWidth("100%");
        stripedGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(stripedGrid);
        layoutColumn4.getStyle().set("flex-grow", "1");
        h32.setText("Bail Category");
        h32.setWidth("max-content");
        textField.setLabel("Bail Name");
        textField.setWidth("min-content");
        numberField.setLabel("Number field");
        numberField.setWidth("min-content");
        price.setLabel("Price");
        price.setWidth("min-content");
        getContent().add(tabSheet);
        getContent().add(layoutRow);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(h1);
        layoutColumn2.add(comboBox);
        layoutColumn2.add(buttonPrimary);
        layoutColumn2.add(progressBar);
        layoutRow.add(layoutColumn3);
        layoutColumn3.add(tabSheet2);
        layoutColumn3.add(link);
        layoutColumn3.add(routerLink);
        layoutColumn3.add(stripedGrid);
        layoutRow.add(layoutColumn4);
        layoutColumn4.add(h32);
        layoutColumn4.add(textField);
        layoutColumn4.add(numberField);
        layoutColumn4.add(price);
    }

    private void setTabSheetSampleData(TabSheet tabSheet) {
        tabSheet.add("Dashboard", new Div(new Text("This is the Dashboard tab content")));
        tabSheet.add("Payment", new Div(new Text("This is the Payment tab content")));
        tabSheet.add("Shipping", new Div(new Text("This is the Shipping tab content")));
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

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
}
