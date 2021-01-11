package modules.admin.Startup.actions;

import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.web.WebContext;

import modules.admin.Startup.StartupExtension;

public class SaveChanges implements ServerSideAction<StartupExtension> {

	private static final long serialVersionUID = -2131639715230917502L;

	@Override
	public ServerSideActionResult<StartupExtension> execute(StartupExtension bean, WebContext webContext) throws Exception {

		// update the configuration
		bean.saveConfiguration();

		return new ServerSideActionResult<>(bean);
	}

}
