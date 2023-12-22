package com.darkstudio.webApp;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CantLoadAContent {
    public VerticalLayout createErrorMessage(String title, Exception e){
        try {
            VerticalLayout verticalLayout = new VerticalLayout();
            verticalLayout.getStyle().set("align-items", "center");
            verticalLayout.getStyle().set("margin", "auto");

            H1 noNewsYet = new H1(title);
            noNewsYet.getStyle().set("color", "gray");
            noNewsYet.getStyle().set("margin", "auto");
            noNewsYet.getStyle().set("padding", "1%");
            verticalLayout.add(noNewsYet);

            H6 reason = new H6(e.toString());
            reason.getStyle().set("color", "red");
            reason.getStyle().set("margin", "auto");
            reason.getStyle().set("text-decoration", "underline");
            verticalLayout.add(reason);

            return verticalLayout;
        }catch (Exception ex){
            new DisplayExceptions(ex).display();
        }
        return null;
    }
}
