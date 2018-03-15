package modules.admin.ReportDesign.actions;

import modules.admin.ReportDesign.ReportDesignBizlet;
import modules.admin.domain.ReportDesign;
import org.skyve.content.MimeType;
import org.skyve.impl.generate.jasperreports.*;
import org.skyve.impl.persistence.AbstractPersistence;
import org.skyve.impl.util.ReportUtil;
import org.skyve.metadata.controller.DownloadAction;
import org.skyve.metadata.customer.Customer;
import org.skyve.metadata.model.document.Document;
import org.skyve.metadata.module.Module;
import org.skyve.metadata.user.User;
import org.skyve.report.ReportFormat;
import org.skyve.web.WebContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Preview extends DownloadAction<ReportDesign> {

	private static final long serialVersionUID = -8203773871581974793L;

	@Override
	public Download download(ReportDesign bean, WebContext webContext) throws Exception {
		final DesignSpecification designSpecification = ReportDesignBizlet.specificationFromDesignBean(bean);
		final ReportDesignGenerator generator = new ReportDesignGeneratorFactory()
				.getGeneratorForDesign(designSpecification);

		generator.populateDesign(designSpecification);

		final User user = AbstractPersistence.get().getUser();
		final Customer customer = user.getCustomer();
		final Module module = customer.getModule(designSpecification.getModuleName());
		final Document document = module.getDocument(customer, designSpecification.getDocumentName());

		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final String reportName = String.format("%s - %s.pdf", designSpecification.getModuleName(), designSpecification.getDocumentName());

		final JasperReportRenderer reportRenderer = new JasperReportRenderer(designSpecification);

		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(JasperReportRenderer.DESIGN_SPEC_PARAMETER_NAME, designSpecification);
		ReportUtil.runReport(reportRenderer.getReport(),
				user,
				document,
				parameters,
				// TODO: We could populate the new instance with random data.
				document.newInstance(user),
				ReportFormat.pdf,
				baos);

		return new Download(reportName, new ByteArrayInputStream(baos.toByteArray()), MimeType.pdf);
	}

}