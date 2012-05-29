package org.drools.guvnor.client.workbench.menu;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.drools.guvnor.client.mvp.IPlaceRequest;
import org.drools.guvnor.client.mvp.PlaceManager;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;

@Singleton
@ApplicationScoped
public class GuvnorMenu extends Composite {

    @Inject
    public GuvnorMenu(final IOCBeanManager manager,
                      final PlaceManager placeManager) {

        Button add = new Button( "Add (mr. Rikkola)" );

        add.addClickHandler( new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

                final Set<IPlaceRequest> places = new HashSet<IPlaceRequest>();

                Collection<IOCBeanDef> beans = manager.lookupBeans( IPlaceRequest.class );

                for ( IOCBeanDef placeBean : beans ) {
                    final IPlaceRequest instance = (IPlaceRequest) placeBean.getInstance();
                    places.add( instance );
                }

                SelectPlacePopup popup = new SelectPlacePopup( places );
                popup.addSelectionHandler( new SelectionHandler<IPlaceRequest>() {
                    @Override
                    public void onSelection(SelectionEvent<IPlaceRequest> event) {
                        placeManager.goTo( event.getSelectedItem().getPlace() );
                    }
                } );

                popup.show();
            }
        } );

        initWidget( add );
    }
}