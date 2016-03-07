openfire-oauth-auth-provider
==========================

AuthProvider for Openfire which will rely on third party oauth server to perform authentication. 

Installation
------------
1. Build the package

		mvn clean package

2. Copy the jar-with-dependencies into the openfire/lib directory

		cp target/openfire-oauth-plugin-x.x.x-with-dependencies.jar /opt/openfire/lib
		
3. Add/update the following openfire server properties:

		hybridAuthProvider.primaryProvider.className = [current value of provider.auth.className]
		
		provider.auth.className = org.jivesoftware.openfire.auth.HybridAuthProvider
	
		hybridAuthProvider.secondaryProvider.className = com.rmd.openfire.auth.OauthAuthProvider

4. Add any extra configuration properties as required (see *Configuration* below)

5. Restart openfire

		/etc/init.d/openfire restart
		
Configuration
-------------
