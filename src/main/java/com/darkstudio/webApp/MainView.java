package com.darkstudio.webApp;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;

@Route("main")
public class MainView extends VerticalLayout {
    public MainView() {
        initMain();
    }

    private void initMain(){
        try {
            getStyle().set("height", "100%");
            getStyle().set("overflow-y", "true");
            getStyle().set("margin", "0");
            getStyle().set("display", "block");
            getStyle().set("padding", "0");

            VerticalLayout mainLayout = new VerticalLayout();
            mainLayout.setSizeFull();
            mainLayout.getStyle().set("background-color", "#ccc");
            mainLayout.getStyle().set("background-blend-mode", "multiply");
            mainLayout.getStyle().set("background-repeat", "no-repeat");
            mainLayout.getStyle().set("background-size", "cover");
            mainLayout.getStyle().set("background-image", new Variables().getBgImage());
            mainLayout.getStyle().set("align-items", "center");

            createHeader();
            add(mainLayout);
            createAdventuresBlock();
            createSiteEnd();

            H2 welcomeText = new H2("Messenger - место для вашего общения с друзьями");
            welcomeText.getStyle().set("color", "white");
            welcomeText.getStyle().set("margin", "auto");

            H4 productName = new H4("𝓑𝔂 𝓓𝓪𝓻𝓴 𝓢𝓽𝓾𝓭𝓲𝓸");
            productName.getStyle().set("color", "white");

            VerticalLayout labelsVertLayout = new VerticalLayout();
            labelsVertLayout.getStyle().set("align-items", "center");
            labelsVertLayout.getStyle().set("margin", "auto");
            labelsVertLayout.getStyle().set("padding-bottom", "10%");
            labelsVertLayout.add(welcomeText);
            labelsVertLayout.add(productName);

            mainLayout.add(labelsVertLayout);
        }catch (Exception ex){
            new DisplayExceptions(ex).display();
        }
    }

    private void createHeader() {
        try {
            Header header1 = new HeaderClass().createHeader("Dark Studio", new int[]{0, 0, 0, 1});
            add(header1);
        }catch (Exception e){
            new DisplayExceptions(e).display();
        }
    }

    private void createAdventuresBlock(){
       VerticalLayout verticalLayout = new VerticalLayout();
       verticalLayout.getStyle().set("align-items", "center");
       verticalLayout.getStyle().set("background", "rgb(12, 23, 39)");

       H1 title = new H1("Почему Messenger?");
       title.getStyle().set("color", "white");

       VerticalLayout verticalLayout1 = new VerticalLayout();
       verticalLayout1.getStyle().set("padding", "2%");
       verticalLayout1.getStyle().set("background", "#08308825");
       verticalLayout1.getStyle().set("border", "solid white");
       verticalLayout1.getStyle().set("border-radius", "13px");
       verticalLayout1.getStyle().set("width", "auto");

       verticalLayout.add(title);
       verticalLayout.add(verticalLayout1);

       HorizontalLayout simpleUI = adventuresBlock("1. Интуитивный интерфейс", "Вы сможете легко разобраться в интерфейсе");
       HorizontalLayout highPerformance = adventuresBlock("2. Высокая производительность", "Приложение будет быстро работать даже на старых устройствах");
       HorizontalLayout aLotOfFunctions = adventuresBlock("3. Множество функций", "Создание скриншотов, погода, закрепление окна и множество других функций");
       HorizontalLayout crossplatform = adventuresBlock("4. Кросплатформенность", "Приложение запустится на любой ОС: Windows, Linux, MAC OS");

       verticalLayout1.add(simpleUI);
       verticalLayout1.add(highPerformance);
       verticalLayout1.add(aLotOfFunctions);
       verticalLayout1.add(crossplatform);

       add(verticalLayout);
    }

    private HorizontalLayout adventuresBlock(String title, String description){
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidthFull();

        H6 h6 = new H6(description);
        h6.getStyle().set("color", "white");

        H2 blockTitle = new H2(title);
        blockTitle.getStyle().set("color", "white");
        horizontalLayout.add(new VerticalLayout(){{
            add(blockTitle);
            add(h6);
        }});

        return horizontalLayout;
    }

    private void createSiteEnd(){
        VerticalLayout horizontalLayout = new VerticalLayout();
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.getStyle().set("background", "#191919");

        H3 h3 = new H3("Начните свое общение прямо сейчас");
        h3.getStyle().set("color", "white");

        H6 h6 = new H6("Скачивая приложение, вы соглашайтесь с его политикой конфиденциальности");
        h6.getStyle().set("color", "gray");
        h6.getStyle().set("text-decoration", "underline");
        h6.getStyle().set("margin-top", "auto");
        h6.getStyle().set("cursor", "pointer");
        h6.addClickListener(listener -> {
            listener.getSource().getUI().ifPresent(ui -> ui.navigate(LicenceView.class));
        });

        H6 developerLabel = new H6("Dark Studio (2022-2024)");
        developerLabel.getStyle().set("color", "gray");

        Button downloadButton = new Button("Скачать");
        downloadButton.setAutofocus(true);
        downloadButton.getStyle().set("background", "rgb(79,82,178)");
        downloadButton.getStyle().set("color", "white");
        downloadButton.getStyle().set("border-radius", "13px");
        downloadButton.getStyle().set("cursor", "pointer");
        downloadButton.getStyle().set("width", "300px");
        downloadButton.addClickListener(clickListener -> {
           clickListener.getSource().getUI().ifPresent(ui -> ui.navigate(DownloadPage.class));
        });

        horizontalLayout.add(h3);
        horizontalLayout.add(downloadButton);
        horizontalLayout.add(h6);
        horizontalLayout.add(developerLabel);
        add(horizontalLayout);

    }
}
