package de.pdbm.faces4;

import static jakarta.faces.application.StateManager.IS_BUILDING_INITIAL_STATE;
import java.io.IOException;
import java.util.List;

import jakarta.el.MethodExpression;
import jakarta.el.ValueExpression;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.View;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIOutput;
import jakarta.faces.component.html.*;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.facelets.Facelet;

/**
 * Programmatically created facelet with view Id mentioned in the value attribute of @View annotation.
 * 
 * Component Ids must be set.
 */
@View("/programmatic-facelet.xhtml")
@ApplicationScoped
public class ProgrammaticFacelet extends Facelet {
	
    @Override
    public void apply(FacesContext facesContext, UIComponent root) throws IOException {
        if (!facesContext.getAttributes().containsKey(IS_BUILDING_INITIAL_STATE)) {
            return;
        }
        ComponentBuilder components = new ComponentBuilder(facesContext);
        List<UIComponent> rootChildren = root.getChildren();
        UIOutput output = new UIOutput();
        output.setValue("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        rootChildren.add(output);
        HtmlBody body = components.create(HtmlBody.COMPONENT_TYPE);
        rootChildren.add(body);
        HtmlForm form = components.create(HtmlForm.COMPONENT_TYPE);
        form.setId("form");
        body.getChildren().add(form);
        
        HtmlOutputLabel label = components.create(HtmlOutputLabel.COMPONENT_TYPE);
        label.setValue("Enter some text: ");
        form.getChildren().add(label);
        HtmlInputText input = components.create(HtmlInputText.COMPONENT_TYPE);
        input.setId("input");
        form.getChildren().add(input);
        String textEl = "#{backingBean.text}";
        FacesContext fc = FacesContext.getCurrentInstance();
        ValueExpression valueExpression = fc.getApplication().getExpressionFactory().createValueExpression(fc.getELContext(), textEl, String.class);
        input.setValueExpression("value", valueExpression);
        
        HtmlCommandButton actionButton = components.create(HtmlCommandButton.COMPONENT_TYPE);
        String actionEl = "#{backingBean.action}";
        actionButton.setId("button");
        Class<?>[] parameterTypes = new Class[0];
        MethodExpression methodExpression = fc.getApplication().getExpressionFactory().createMethodExpression(fc.getELContext(), actionEl, String.class, parameterTypes); 
        actionButton.setActionExpression(methodExpression);
        actionButton.setValue("Do action");
        form.getChildren().add(actionButton);
        
        HtmlOutputLink indexLink = components.create(HtmlOutputLink.COMPONENT_TYPE);
        indexLink.setValue(fc.getExternalContext().getApplicationContextPath() + "/index.xhtml");
        rootChildren.add(indexLink);
        UIOutput back = new UIOutput();
        back.setValue("back");
        indexLink.getChildren().add(back);
        
        output = new UIOutput();
        output.setValue("</html>");
        rootChildren.add(output);
    }
    
    private static class ComponentBuilder {
    	
        FacesContext facesContext;
        
        ComponentBuilder(FacesContext facesContext) {
            this.facesContext = facesContext;
        }
        
        @SuppressWarnings("unchecked")
        <T> T create(String componentType) {
            return (T) facesContext.getApplication().createComponent(facesContext, componentType, null);
        }
    }
    
}