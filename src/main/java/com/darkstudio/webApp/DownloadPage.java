package com.darkstudio.webApp;

import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("download")
public class DownloadPage extends VerticalLayout {
    VerticalLayout verticalLayout;
    public DownloadPage(){
        initView();
    }

    private void initView() {
        try {
            setSizeFull();
            getStyle().set("overflow-y", "true");
            getStyle().set("background", "#ccc");
            getStyle().set("align-items", "center");
            getStyle().set("background-image", new Variables().getBgImage());
            getStyle().set("background-color", "#ccc");
            getStyle().set("background-blend-mode", "multiply");

            initHeader();
            loadContent();
        }catch (Exception e){
            new DisplayExceptions(e).display();
        }
    }

    private void loadContent() {
        try {
            verticalLayout = new VerticalLayout();
            verticalLayout.getStyle().set("background-color", "white");
            verticalLayout.getStyle().set("opacity", "0.89");
            verticalLayout.getStyle().set("padding", "2%");
            verticalLayout.getStyle().set("border", "solid rgb(79,82,178)");
            verticalLayout.getStyle().set("border-radius", "13px");
            verticalLayout.getStyle().set("width", "auto");
            verticalLayout.getStyle().set("margin", "auto");
            verticalLayout.getStyle().set("box-shadow", "5px 5px 40px black");

            Image productImage = new Image(new StreamResource("simpleUI.png",
                    () -> getClass().getResourceAsStream("/simpleUI.png")), "");
            productImage.getStyle().set("width", "300px");

            verticalLayout.add(productImage);

            H3 blockTitle = new H3("Версия для Windows");
            verticalLayout.add(blockTitle);

            H6 requirements = new H6("""
                    Требования:
                    1.1GB RAM
                    2.Система Windows 10/11+
                    3.Экран с разрешением не менее 1300x900
                    4.Поддержка Desktop""");
            requirements.getStyle().set("white-space", "pre-wrap");
            verticalLayout.add(requirements);

            Button downloadButton = new Button("Скачать");
            downloadButton.getStyle().set("background", "rgb(79,82,178)");
            downloadButton.getStyle().set("color", "white");
            downloadButton.getStyle().set("border-radius", "13px");
            downloadButton.getStyle().set("cursor", "pointer");
            downloadButton.getStyle().set("width", "300px");
            downloadButton.addClickListener(listener -> {
                showThankYouForDownloading();
            });

            verticalLayout.add(downloadButton);

            H3 blockTitleHowToInstall = new H3("Как установить?");
            verticalLayout.add(blockTitleHowToInstall);

            H6 steps = new H6("""
                    1.Скачайте exe-файл
                    2.Установите приложение
                    3.Установите Java 20""");
            steps.getStyle().set("white-space", "pre-wrap");
            verticalLayout.add(blockTitleHowToInstall);
            verticalLayout.add(steps);

            add(verticalLayout);
        }catch (Exception e){
            showLoadError(e);
        }
    }

    private void showThankYouForDownloading(){
        try {
            remove(verticalLayout);

            H1 thankYou = new H1("Спасибо за скачивание!");
            thankYou.getStyle().set("color", "white");

            add(new VerticalLayout() {{
                getStyle().set("margin", "auto");
                getStyle().set("width", "auto");

                H6 steps = new H6("""
                        1.При появлении предупреждения о безопасности, продолжите в любом случае.
                        Предупреждение показывается из-за отсутствия цифровой подписи
                        2.Запустите установщик
                        3.Установите, следуя подсказкам в установщике\s""");
                steps.setWhiteSpace(HasText.WhiteSpace.PRE_WRAP);
                steps.getStyle().set("color", "gray");
                steps.getStyle().set("margin", "auto");

                add(thankYou);
                add(steps);
            }});
        }catch (Exception e){
            add(new CantLoadAContent().createErrorMessage("Не удалось загрузить", e));
        }
    }

    private void showLoadError(Exception e) {
        add(new CantLoadAContent().createErrorMessage("Не удалось отобразить содержимое", e));
    }

    private void initHeader() {
        try {
            Header header = new HeaderClass().createHeader("Приложение", new int[]{1, 0, 0, 0});
            add(header);
        }catch (Exception e){
            new DisplayExceptions(e).display();
        }
    }
}
