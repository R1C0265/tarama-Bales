package com.application.views.updates;

import java.util.Arrays;
import java.util.List;

import com.application.data.Updates;
import com.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import jakarta.annotation.security.PermitAll;

@PageTitle("Updates")
@Route(value = "updates", layout = MainLayout.class)
@Menu(order = 1, icon = "list")
@AnonymousAllowed
@PermitAll
public class UpdatesView extends Div implements AfterNavigationObserver {

    private Grid<Updates> grid = new Grid<>();
    private List<Updates> updates;

    public UpdatesView() {
        addClassName("feed-view");
        setSizeFull();

        // Create the search bar
        TextField searchBar = new TextField();
        searchBar.setPlaceholder("Search updates...");
        searchBar.setWidthFull();
        searchBar.addValueChangeListener(event -> {
            String searchTerm = event.getValue().toLowerCase();
            filterGrid(searchTerm);
        });

        // Configure the grid
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(update -> createCard(update));

        // Add the search bar and grid to the layout
        add(searchBar, grid);
    }

    private HorizontalLayout createCard(Updates update) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        Image image = new Image();
        image.setSrc(update.getImage());
        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span recordedBy = new Span(update.getRecordedBy());
        recordedBy.addClassName("name");
        Span date = new Span(update.getDate());
        date.addClassName("date");
        header.add(recordedBy, date);

        Span title = new Span(update.getTitle());
        title.addClassName("post");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        Icon likeIcon = VaadinIcon.HEART.create();
        likeIcon.addClassName("icon");
        Span category = new Span(update.getCategory());
        category.addClassName("likes");
        Icon commentIcon = VaadinIcon.COMMENT.create();
        commentIcon.addClassName("icon");
        Span amount = new Span(update.getAmount());
        amount.addClassName("comments");

        actions.add(likeIcon, category, commentIcon, amount);

        description.add(header, title, actions);
        card.add(image, description);
        return card;
    }

    private void filterGrid(String searchTerm) {
        List<Updates> filteredUpdates = updates.stream()
            .filter(update -> update.getRecordedBy().toLowerCase().contains(searchTerm) ||
                              update.getTitle().toLowerCase().contains(searchTerm) ||
                              update.getDate().toLowerCase().contains(searchTerm) ||
                              update.getCategory().toLowerCase().contains(searchTerm) ||
                              update.getAmount().toLowerCase().contains(searchTerm))
            .toList();
        grid.setItems(filteredUpdates);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        // Set some data when this view is displayed.
        updates = Arrays.asList(
            createUpdate("https://randomuser.me/api/portraits/men/42.jpg", "John Smith", "May 8",
                    "Added a new Bale", "Bale", "MWK 50000"),
            createUpdate("https://randomuser.me/api/portraits/women/42.jpg", "Abagail Libbie", "May 3",
                    "Recorded a Sale", "Sale", "MWK 30000")
            // Add more Updates objects here...
        );

        grid.setItems(updates);
    }

    private static Updates createUpdate(String image, String recordedBy, String date, String title, String category, String amount) {
        Updates update = new Updates();
        update.setImage(image);
        update.setRecordedBy(recordedBy);
        update.setDate(date);
        update.setTitle(title);
        update.setCategory(category);
        update.setAmount(amount);

        return update;
    }
}
