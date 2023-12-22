package com.darkstudio.webApp;

import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("previous-versions")
public class PreviousVersions extends VerticalLayout {
    public PreviousVersions(){
        initMain();
    }
    List<VerticalLayout> blocks = new ArrayList<>();
    List<Button> buttons = new ArrayList<>();

    private void initMain() {
        setSizeFull();
        getStyle().set("overflow-y", "true");
        getStyle().set("background-image", new Variables().getBgImage());
        getStyle().set("background-size", "cover");
        getStyle().set("background-color", "#ccc");
        getStyle().set("background-blend-mode", "multiply");

        add(new HeaderClass().createHeader("Старые версии", new int[]{0,0,1,0}));
        add(new HorizontalLayout(){{
            setSizeFull();

            add(new VerticalLayout(){{
                getStyle().set("background-color", "transparent");
                getStyle().set("overflow-y", "auto");
                getStyle().set("opacity", "0.89");
                getStyle().set("align-items", "center");
                getStyle().set("margin-top", "5%");
                getStyle().set("height", "auto");

                Page page = UI.getCurrent().getPage();
                page.addBrowserWindowResizeListener(
                        event -> {
                            if(event.getHeight() <= 809) {
                                getStyle().set("margin-top", "19%");
                                for(int i = 0; i!=blocks.size(); i++){
                                    blocks.get(i).getStyle().set("width", "250px");
                                    buttons.get(i).getStyle().set("width", "100px");
                                }
                            } else {
                                getStyle().set("margin-top", "5%");
                                for(int i = 0; i!=blocks.size(); i++){
                                    blocks.get(i).getStyle().set("width", "404px");
                                    buttons.get(i).getStyle().set("width", "300px");
                                }
                            }
                        });

                add(createVersionBlock("1. Messenger v.4", "Является четвертой версией Messenger. Относится к" +
                        "\n Java Edition. Был выпущен в 2023 году. Работает на технологии JavaFX.", new AppInfo("Dark Studio", "4.6", "23FEB28", "Java Edition", "Бесплатное ПО", "130МБ"), 4));
                add(createVersionBlock("2. Messenger v.3", """
                        Является третьей версией Messenger. Относится к
                        Python Edition. Был выпущен в 2022 году. Работает на технологии PyQT5. Можно расценивать как
                        переиздание Messenger v.2""", new AppInfo("Dark Studio", "3.1_02", "22MAY22", "Python Edition", "Бесплатное ПО", "17,9МБ"), 3));
                add(createVersionBlock("3. Messenger v.2", """
                        Является второй версией Messenger. Относится к
                        Python Edition. Работает на технологии Tkinter.
                        Впервые появилась возможность переписываться.
                        Чат работает на технологии PyWebIO.""", new AppInfo("Dark Studio", "2.0", "22MAY07", "Python Edition", "Бесплатное ПО", "19,9МБ"), 2));
                add(createVersionBlock("4. Messenger v.1", """
                        Является первой версией Messenger. Относится к
                        Python Edition. Не было возможности переписываться. Была
                        возможность только отправить письмо на Email. Не
                        сохранилась в нормальном виде""", new AppInfo("Dark Studio", "1.0_1", "22JUN22", "Python Edition", "Бесплатное ПО", "40,8МБ"), 1));
            }});
        }});
    }

    private VerticalLayout createVersionBlock(String title, String description, AppInfo appInfo, int version) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.getStyle().set("margin", "auto");
        verticalLayout.getStyle().set("border-radius", "13px");
        verticalLayout.getStyle().set("border", "solid");
        verticalLayout.getStyle().set("width", "404px");
        verticalLayout.getStyle().set("background", "rgb(19, 23, 43)");

        H2 h2 = new H2(title);
        h2.getStyle().set("color", "white");

        H6 h6 = new H6(description);
        h6.setWhiteSpace(HasText.WhiteSpace.PRE_WRAP);
        h6.getStyle().set("color", "white");

        verticalLayout.add(h2);
        verticalLayout.add(h6);

        Button downloadButton = new Button("Скачать");
        downloadButton.getStyle().set("background", "rgb(79,82,178)");
        downloadButton.getStyle().set("color", "white");
        downloadButton.getStyle().set("border-radius", "13px");
        downloadButton.getStyle().set("cursor", "pointer");
        downloadButton.getStyle().set("width", "300px");

        if(version != 1) {
            verticalLayout.add(downloadButton);
        }

        H6 appInformation = new H6("Разработчик: "+appInfo.getDeveloper() + "\n" +
                "Издание: " + appInfo.getEdition() + "\n" +
                "Версия: " + appInfo.getVersion() + "\n" +
                "Сборка: " + appInfo.getBuild() + "\n" +
                "Лицензия: " + appInfo.getLicence() + "\n" +
                "Размер: " + appInfo.getSize());
        appInformation.getStyle().set("padding-top", "2%");
        appInformation.getStyle().set("color", "white");
        appInformation.setWhiteSpace(HasText.WhiteSpace.PRE_WRAP);
        verticalLayout.add(appInformation);

        buttons.add(downloadButton);
        blocks.add(verticalLayout);
        return verticalLayout;
    }
}
