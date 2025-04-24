package com.application.views.details;

import java.util.Optional;
import com.application.data.Purchase;
import com.application.services.PurchaseService;
import com.application.views.MainLayout;
import com.application.views.sales.SalesView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Purchase Details")
@Route(value = "details/:purchaseID", layout = MainLayout.class)
@PermitAll
public class DetailsView extends Div implements BeforeEnterObserver {

    private final PurchaseService purchaseService;
    private Purchase purchase;

    public DetailsView(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> purchaseId = event.getRouteParameters().get("purchaseID").map(Long::parseLong);
        if (purchaseId.isPresent()) {
            Optional<Purchase> purchaseFromBackend = purchaseService.get(purchaseId.get());
            if (purchaseFromBackend.isPresent()) {
                this.purchase = purchaseFromBackend.get();
                displayDetails();
            } else {
                Notification.show("Purchase not found", 3000, Notification.Position.BOTTOM_START);
                UI.getCurrent().navigate(SalesView.class);
            }
        }
    }

    private void displayDetails() {
        add(new H3("Purchase Details"));
        add(new Div(new Text("Customer Name: " + purchase.getCustomerName())));
        add(new Div(new Text("Bail Name: " + purchase.getBailName())));
        add(new Div(new Text("Amount Of Items: " + purchase.getAmounOfItems())));
        add(new Div(new Text("Price: " + purchase.getPrice())));
        add(new Div(new Text("Cashier: " + purchase.getCashier())));
        add(new Div(new Text("Purchase Date: " + purchase.getPurchaseDate())));
    }
}
