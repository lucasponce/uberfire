package org.uberfire.annotations.processors;

import org.uberfire.client.annotations.OnStart;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.UberView;
import org.uberfire.mvp.PlaceRequest;

@WorkbenchScreen(identifier = "test12")
public class WorkbenchScreenTest12 {

    @WorkbenchPartView
    public UberView<WorkbenchScreenTest12> getView() {
        return null;
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "title";
    }

    @OnStart
    public void onStart( final PlaceRequest place ) {
    }

}
