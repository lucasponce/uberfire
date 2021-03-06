/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.security.server.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import org.uberfire.security.Identity;
import org.uberfire.security.Subject;
import org.uberfire.security.authz.AuthorizationManager;
import org.uberfire.security.impl.IdentityImpl;

public class SecurityFactory {

    static private final ThreadLocal<Subject> subjects = new ThreadLocal<Subject>();

    static private AuthorizationManager authzManager = null;

    public static void setSubject( final Subject subject ) {
        subjects.set( subject );
    }

    public static void setAuthzManager( final AuthorizationManager authzManager ) {
        SecurityFactory.authzManager = authzManager;
    }

    @Produces
    @RequestScoped
    public static Identity getIdentity() {
        return new IdentityImpl( subjects.get().getName(), subjects.get().getRoles(), subjects.get().getProperties() );
    }

    @Produces
    @ApplicationScoped
    public static AuthorizationManager getAuthzManager() {
        return authzManager;
    }

}
