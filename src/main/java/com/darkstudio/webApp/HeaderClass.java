package com.darkstudio.webApp;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

public class HeaderClass {
    public com.vaadin.flow.component.html.Header createHeader(String caption, int[] selectedTabs){
        try {
            com.vaadin.flow.component.html.Header header1 = new com.vaadin.flow.component.html.Header();
            header1.getStyle().set("padding", "8px");
            header1.getStyle().set("background", "#191919");
            header1.getStyle().set("position", "fixed");
            header1.getStyle().set("top", "0");
            header1.getStyle().set("left", "0");
            header1.getStyle().set("right", "0");
            header1.getStyle().set("display", "flex");
            header1.getStyle().set("opacity", "0.89");
            header1.getStyle().set("z-index", "5000");

            HorizontalLayout header = new HorizontalLayout();
            header.getStyle().set("background", "#191919");
            header.getStyle().set("width", "100%");
            header.getStyle().set("height", "10%");
            header.getStyle().set("align-items", "center");

            H1 title = new H1(caption);
            title.getStyle().set("color", "white");
            title.getStyle().set("margin-right", "auto");
            title.getStyle().set("padding-left", "1%");
            header.add(title);

            Tab tab1 = new Tab("Приложение");
            tab1.getStyle().set("color", "white");
            tab1.getStyle().set("cursor", "pointer");
            tab1.setTooltipText("Скачать приложение");

            Tab tab2 = new Tab("Новости");
            tab2.getStyle().set("color", "white");
            tab2.getStyle().set("cursor", "pointer");
            tab2.setTooltipText("Последние новости приложения");

            Tab tab3 = new Tab("Старые версии");
            tab3.getStyle().set("color", "white");
            tab3.getStyle().set("cursor", "pointer");
            tab3.setTooltipText("Предыдущие версии Messenger");

            Tab tab4 = new Tab("Главная");
            tab4.getStyle().set("color", "white");
            tab4.getStyle().set("cursor", "pointer");
            tab4.setTooltipText("Главная страница");

            Tabs tabs = new Tabs();
            tabs.add(tab4, tab3, tab2, tab1);

            Page page = UI.getCurrent().getPage();
            page.addBrowserWindowResizeListener(
                    event -> {
                        if(event.getWidth() <= 500) {
                            tabs.getStyle().set("width", "200px");
                            title.getStyle().set("font-size", "15pt");
                        } else{
                            tabs.getStyle().set("width", "auto");
                            title.getStyle().set("font-size", "24pt");
                        }
                    });

            tabs.addSelectedChangeListener(listener -> {
                switch (tabs.getSelectedIndex()) {
                    case 3 -> {
                        title.setText("Приложение");
                        tab1.getUI().ifPresent(ui -> ui.navigate(DownloadPage.class));
                    }
                    case 2 -> {
                        title.setText("Новости");
                        tab1.getUI().ifPresent(ui -> ui.navigate(NewsPage.class));
                    }
                    case 1 -> {
                        title.setText("Старые версии");
                        tab1.getUI().ifPresent(ui -> ui.navigate(PreviousVersions.class));
                    }
                    case 0 -> {
                        title.setText("Dark Studio");
                        tab1.getUI().ifPresent(ui -> ui.navigate(MainView.class));
                    }
                }
            });

            Tab selectedTab = null;
            if (selectedTabs[0] == 1)
                selectedTab = tab1;
            else if (selectedTabs[1] == 1)
                selectedTab = tab2;
            else if (selectedTabs[2] == 1)
                selectedTab = tab3;
            else if (selectedTabs[3] == 1) {
                selectedTab = tab4;
            }
            tabs.setSelectedTab(selectedTab);

            header.add(tabs);
            header1.add(header);

            return header1;
        }catch (Exception e){
            new DisplayExceptions(e).display();
        }
        return null;
    }
}
