package com.application.views.details;

import com.application.views.MainLayout;
import com.application.views.allbails.AllBailsView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.Optional;
import com.application.data.Bail;
import com.application.services.BailService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.BeforeEnterEvent;
import jakarta.annotation.security.PermitAll;

@PageTitle("Bail Details")
@Route(value = "bail-details/:bailID", layout = MainLayout.class)
@PermitAll
public class BailsDetailsView extends Div implements BeforeEnterObserver {

    private final BailService bailService;
    private Bail bail;

    public BailsDetailsView(BailService bailService) {
        this.bailService = bailService;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> bailId = event.getRouteParameters().get("bailID").map(Long::parseLong);
        if (bailId.isPresent()) {
            Optional<Bail> bailFromBackend = bailService.get(bailId.get());
            if (bailFromBackend.isPresent()) {
                this.bail = bailFromBackend.get();
                displayDetails();
            } else {
                Notification.show("Bail not found", 3000, Notification.Position.BOTTOM_START);
                UI.getCurrent().navigate(AllBailsView.class);
            }
        }
    }

    private void displayDetails() {
        add(new H3("Bail Details"));
        add(new Div(new Text("Bail Name: " + bail.getBailName())));
        add(new Div(new Text("Amount Of Items: " + bail.getAmounOfItems())));
        add(new Div(new Text("Bail Price: " + bail.getBailPrice())));
        add(new Div(new Text("Date Of Purchase: " + bail.getDateOfPurchase())));
    }

    /* package com.application.views.personform;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Person Form")
@Route("person-form")
@Menu(order = 2, icon = LineAwesomeIconUrl.USER)
@AnonymousAllowed
public class PersonFormView extends Composite<VerticalLayout> {

    public PersonFormView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        DatePicker datePicker = new DatePicker();
        TextField textField3 = new TextField();
        EmailField emailField = new EmailField();
        TextField textField4 = new TextField();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Personal Information");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        textField.setLabel("First Name");
        textField2.setLabel("Last Name");
        datePicker.setLabel("Birthday");
        textField3.setLabel("Phone Number");
        emailField.setLabel("Email");
        textField4.setLabel("Occupation");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Save");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancel");
        buttonSecondary.setWidth("min-content");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textField);
        formLayout2Col.add(textField2);
        formLayout2Col.add(datePicker);
        formLayout2Col.add(textField3);
        formLayout2Col.add(emailField);
        formLayout2Col.add(textField4);
        layoutColumn2.add(layoutRow);
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonSecondary);
    }
}
 */
}
