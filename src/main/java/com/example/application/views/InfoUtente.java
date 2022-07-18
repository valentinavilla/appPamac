package com.example.application.views;

import java.util.Optional;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.example.application.data.entity.Contact.genere;
import com.example.application.views.list.ContactForm;
import com.example.application.views.questionario.QuestionarioView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "InfoUtente",layout=MainLayout.class) 
@PageTitle("InfoUtente")
public class InfoUtente extends VerticalLayout implements HasUrlParameter<String> ,BeforeEnterObserver {
    //implements hasUrlParameters<String>
    //implements hasRouteParameters
    //Implements beforeEnterObserver 
    private Contact contatto;
    private Tab paziente;
	private Tab questionario;
	private Tab altro;
	private VerticalLayout content=new VerticalLayout();
    private ContactForm form;

    public InfoUtente(){
        add( getTabs(), content );
    }

    public InfoUtente(String s){
        String[] stringa=s.split(",");
        Contact c=new Contact();
        c.setFirstName(stringa[0]);
        c.setLastName(stringa[1]);
        c.setStatus(new Status(stringa[2]));
        c.setEmail(stringa[3]);
        if (stringa[4]=="M") {c.setGenere(genere.M);}
        else{c.setGenere(genere.F);}

        new InfoUtente(c);
    }

    public InfoUtente(Contact c){
        this.contatto=c;
        addClassName("infoUtente-view");
        //setDefaultHorizontalComponentAlignment(Alignment.CENTER); 
        //configureInfo(c);
        //configureForm();
        add(getTabs(),content);
    }

    /* 
    private void configureForm() {
        form = new ContactForm( service.findAllStatuses()); 
        form.setWidth("25em");

        form.addListener(ContactForm.SaveEvent.class, this::saveContact); 
        form.addListener(ContactForm.DeleteEvent.class, this::deleteContact); 
        form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());
    }*/

    public InfoUtente(Optional<String> optional) {
        if(optional.isEmpty()){new InfoUtente();}
        else {new InfoUtente(optional.get());}
    }

    private Component configureInfo(Contact c) {
        VerticalLayout Vl=new VerticalLayout();
        Vl.add("CIAO");
        return Vl;
    }

    private Tabs getTabs() {
        paziente = new Tab("Paziente");
        questionario= new Tab("Questionario");
        altro= new Tab("Modifica");

        Tabs tabs = new Tabs(paziente, questionario, altro);
        tabs.setWidthFull();
        tabs.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
        tabs.addSelectedChangeListener(event ->
	    setContent(event.getSelectedTab())
        );

        
		content = new VerticalLayout();
		content.setSpacing(false);
		setContent(tabs.getSelectedTab());

		return tabs;
	}


     private void setContent(Tab tab) {
		content.removeAll();

		if (tab.equals(paziente)) {
            content.add(configureInfo(contatto));
			content.add(new Paragraph("qui vedrai le info del paziente"));
            content.add(configureInfo(contatto));
		} else if (tab.equals(questionario)) {
			content.add(new QuestionarioView());
		} else {
			content.add(new Paragraph("Qui vedrai eventuali richieste/annotazioni"));
		}
	}

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
       
       //new InfoUtente(event.getRouteParameters().get("parametro"));
        new InfoUtente();
    }

    
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // TODO Auto-generated method stub
        //new InfoUtente(event.getRouteParameters().getValue("s"));
        new InfoUtente();
    }
    

}



