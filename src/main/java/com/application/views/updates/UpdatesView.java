package com.application.views.updates;

import java.util.Arrays;
import java.util.List;

import com.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PageTitle("Updates")
@Route(value = "updates", layout = MainLayout.class)
@PermitAll
public class UpdatesView extends Div implements AfterNavigationObserver {

    Grid<UpdatesEntity> grid = new Grid<>();

    public UpdatesView() {
        addClassName("feed-view");
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(update -> createCard(update));
        add(grid);
    }

    private HorizontalLayout createCard(UpdatesEntity updatesEntity) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");


        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);
        


        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(updatesEntity.getPost());
        name.addClassName("post");
        Span date = new Span(updatesEntity.getDate());
        date.addClassName("date");
        header.add(name, date);
        

        description.add(header);
        card.add(description);
        return card;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        // Set some data when this view is displayed.
        List<UpdatesEntity> persons = Arrays.asList( //
                createPerson( "May 8",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly"), createPerson( "May 8",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly")

        );

        grid.setItems(persons);
    }

    private static UpdatesEntity createPerson(String date, String post) {
        UpdatesEntity p = new UpdatesEntity();
        p.setDate(date);
        p.setPost(post);
        return p;
    }

}
