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
}
