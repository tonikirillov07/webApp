package com.darkstudio.webApp;

import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Route("news")
public class NewsPage extends VerticalLayout {
    List<String> titles = new ArrayList<>();
    List<String> texts = new ArrayList<>();
    List<VerticalLayout> newsBlocks = new ArrayList<>();
    List<H2> titlesH2 = new ArrayList<>();
    int jsonFilesCounter = 0;
    public NewsPage(){
        initPage();
    }

    private void initPage() {
        try {
            setSizeFull();
            getStyle().set("overflow-y", "true");
            getStyle().set("background-image", new Variables().getBgImage());
            getStyle().set("background-size", "cover");
            getStyle().set("background-color", "#ccc");
            getStyle().set("background-blend-mode", "multiply");

            HeaderClass headerClass = new HeaderClass();
            add(headerClass.createHeader("Новости", new int[]{0, 1, 0, 0}));

            String[] newsFiles = new File("texts/news/").list();

            assert newsFiles != null;
            if(newsFiles.length != 0) {
                loadNews(newsFiles);
            }else{
                noNewsYet();
            }
        }catch (Exception e){
            new DisplayExceptions(e).display();
        }
    }

    private void noNewsYet() {
        H1 noNewsYet = new H1("Пока-что здесь ничего нет...");
        noNewsYet.getStyle().set("color", "gray");
        noNewsYet.getStyle().set("margin", "auto");
        add(noNewsYet);
    }

    private void loadNews(String[] newsFiles) {
        try {
            VerticalLayout content = new VerticalLayout();
            content.getStyle().set("margin-top", "3%");
            content.getStyle().set("overflow-y", "auto");
            content.getStyle().set("width", "100%");
            content.getStyle().set("height", "auto");
            content.getStyle().set("align-items", "center");

            TextField searchField = new TextField();

            Page page = UI.getCurrent().getPage();
            page.addBrowserWindowResizeListener(event -> {if(event.getHeight() <= 809) {
                content.getStyle().set("margin-top", "10%");

                for(int i = 0; i!=newsBlocks.size(); i++){
                    newsBlocks.get(i).getStyle().set("width", "250px");
                }

                for(int i = 0; i!=titlesH2.size(); i++){
                    titlesH2.get(i).getStyle().set("font-size", "15pt");
                }

                searchField.getStyle().set("width", "240px");
            } else {
                content.getStyle().set("margin-top", "3%");

                for(int i = 0; i!=newsBlocks.size(); i++){
                    newsBlocks.get(i).getStyle().set("width", "500px");
                }

                for(int i = 0; i!=titlesH2.size(); i++){
                    titlesH2.get(i).getStyle().set("font-size", "22pt");
                }

                searchField.getStyle().set("width", "490px");
            }});

            searchField.getStyle().set("width", "490px");
            searchField.getStyle().set("color", "black");
            searchField.getStyle().set("margin", "auto");
            searchField.getStyle().set("background-color", "white");
            searchField.getStyle().set("border", "solid rgb(79,82,178)");
            searchField.getStyle().set("border-radius", "13px");
            searchField.setPlaceholder("Поиск новостей");
            searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));

            List<VerticalLayout> whatToDisplay = new ArrayList<>();


            searchField.addValueChangeListener(listener -> {
                AtomicInteger resultOnTitles = new AtomicInteger();

                if(!searchField.getValue().isEmpty()) {
                    for (int i = 0; i != titles.size(); i++) {
                        if (titles.get(i).contains(searchField.getValue())) {
                            for (int ai = 0; ai != newsBlocks.size(); ai++) {
                                H2 h2 = (H2) newsBlocks.get(ai).getChildren().toArray()[0];
                                if (h2.getText().contains(searchField.getValue())) {
                                    whatToDisplay.add(newsBlocks.get(ai));
                                    resultOnTitles.set(1);
                                }
                            }

                            if(resultOnTitles.get() == 0) {
                                for (int ii = 0; ii != texts.size(); ii++) {
                                    if (texts.get(ii).contains(searchField.getValue())) whatToDisplay.add(newsBlocks.get(ii));
                                }
                            }

                            for (int bi = 0; bi != newsBlocks.size(); bi++) {
                                if (!whatToDisplay.contains(newsBlocks.get(bi))) newsBlocks.get(bi).setVisible(false);
                            }

                            System.out.println(resultOnTitles.get());
                            /*
                            System.out.println(titles.get(i));
                            System.out.println(Arrays.toString(whatToDisplay.toArray()));

                             */
                        }
                    }

                }else{
                    for (int bi = 0; bi != newsBlocks.size(); bi++) {
                        newsBlocks.get(bi).setVisible(true);
                    }
                    whatToDisplay.clear();
                }
            });

            content.add(searchField);

            for (int i = 0; i != Objects.requireNonNull(newsFiles).length; i++) {
                if (newsFiles[i].endsWith(".json")) {
                    jsonFilesCounter++;
                    Object object = new JSONParser().parse(new FileReader("texts/news/" + newsFiles[i]));
                    JSONObject jsonObject1 = (JSONObject) object;

                    content.add(createNewsBlock(jsonObject1.get("title").toString(), jsonObject1.get("text").toString()));
                    titles.add(jsonObject1.get("title").toString());
                    texts.add(jsonObject1.get("text").toString());
                }
            }

            if(jsonFilesCounter == 0){
                content.removeAll();
                noNewsYet();
            }

            Scroller scroller = new Scroller();
            scroller.getStyle().set("margin", "auto");
            scroller.getStyle().set("height", "100%");
            scroller.getStyle().set("width", "100%");
            scroller.getStyle().set("display", "contents");
            scroller.setContent(content);

            add(scroller);
        }catch (Exception e){
            showLoadError(e);
        }
    }

    private void showLoadError(Exception e) {
        add(new CantLoadAContent().createErrorMessage("Не удалось отобразить новости", e));
    }

    private VerticalLayout createNewsBlock(String caption, String newsText){
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.getStyle().set("background-color", "rgb(19, 23, 43)");
        verticalLayout.getStyle().set("border", "solid white");
        verticalLayout.getStyle().set("border-radius", "13px");
        verticalLayout.getStyle().set("margin", "auto");
        verticalLayout.getStyle().set("width", "500px");
        verticalLayout.getStyle().set("align-items", "center");
        verticalLayout.getStyle().set("opacity", "0.87");

        H2 blockTitle = new H2(caption);
        blockTitle.getStyle().set("color", "white");
        verticalLayout.add(blockTitle);
        titlesH2.add(blockTitle);

        H6 text = new H6(newsText);
        text.getStyle().set("color", "white");
        text.setWhiteSpace(HasText.WhiteSpace.PRE_WRAP);
        verticalLayout.add(new Scroller(){{
            getStyle().set("height", "114px");
            setContent(text);
        }});

        newsBlocks.add(verticalLayout);

        return verticalLayout;
    }
}
