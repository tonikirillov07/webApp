package com.darkstudio.webApp;

import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Scanner;

@Route("licence")
public class LicenceView extends VerticalLayout {
    public LicenceView(){
        initMain();
    }

    private void initMain() {
        try {
            add(new HeaderClass().createHeader("Лицензия", new int[]{0, 0, 0, 0}));

            getStyle().set("background-image", new Variables().getBgImage());
            getStyle().set("background-size", "cover");
            getStyle().set("background-blend-mode", "multiply");
            getStyle().set("background-color", "#ccc");
            getStyle().set("overflow-y", "auto");
            setSizeFull();
            setAlignItems(Alignment.CENTER);

            H6 licenceText = new H6(readFile(new File("texts/licence.txt")));
            licenceText.getStyle().set("color", "white");
            licenceText.getStyle().set("margin-top", "16%");
            licenceText.setWhiteSpace(HasText.WhiteSpace.PRE_WRAP);

            add(licenceText);
        }catch (Exception e){
            new DisplayExceptions(e).display();
        }
    }

    private @Nullable String readFile(File file) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()){
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
            return stringBuilder.toString();
        }catch (Exception e){
            add(new CantLoadAContent().createErrorMessage("Не удалось загрузить лицензию", e));
        }
        return null;
    }
}
