package modules.admin.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.admin.Group.GroupExtension;
import modules.admin.User.UserExtension;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateTime;
import org.skyve.domain.types.Enumeration;
import org.skyve.domain.types.Timestamp;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.ChangeTrackingArrayList;
import org.skyve.impl.domain.types.jaxb.DateTimeMapper;
import org.skyve.impl.domain.types.jaxb.TimestampMapper;
import org.skyve.metadata.model.document.Bizlet.DomainValue;

/**
 * User
 * 
 * @depend - - - WizardState
 * @depend - - - GroupSelection
 * @navhas n dataGroup 0..1 DataGroup
 * @navhas n assignedRoles 0..n UserRole
 * @navhas n contact 1 Contact
 * @navcomposed 1 roles 0..n UserRole
 * @navhas n groups 0..n Group
 * @navhas n newGroup 0..1 Group
 * @navcomposed 1 candidateContacts 0..n UserCandidateContact
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public abstract class User extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "admin";
	/** @hidden */
	public static final String DOCUMENT_NAME = "User";

	/** @hidden */
	public static final String userNamePropertyName = "userName";
	/** @hidden */
	public static final String passwordPropertyName = "password";
	/** @hidden */
	public static final String generatedPasswordPropertyName = "generatedPassword";
	/** @hidden */
	public static final String createdDateTimePropertyName = "createdDateTime";
	/** @hidden */
	public static final String homeModulePropertyName = "homeModule";
	/** @hidden */
	public static final String newPasswordPropertyName = "newPassword";
	/** @hidden */
	public static final String confirmPasswordPropertyName = "confirmPassword";
	/** @hidden */
	public static final String legacyIdPropertyName = "legacyId";
	/** @hidden */
	public static final String passwordExpiredPropertyName = "passwordExpired";
	/** @hidden */
	public static final String passwordLastChangedPropertyName = "passwordLastChanged";
	/** @hidden */
	public static final String passwordResetTokenPropertyName = "passwordResetToken";
	/** @hidden */
	public static final String passwordHistoryPropertyName = "passwordHistory";
	/** @hidden */
	public static final String authenticationFailuresPropertyName = "authenticationFailures";
	/** @hidden */
	public static final String lastAuthenticationFailurePropertyName = "lastAuthenticationFailure";
	/** @hidden */
	public static final String contactPropertyName = "contact";
	/** @hidden */
	public static final String dataGroupPropertyName = "dataGroup";
	/** @hidden */
	public static final String groupsPropertyName = "groups";
	/** @hidden */
	public static final String rolesPropertyName = "roles";
	/** @hidden */
	public static final String wizardStatePropertyName = "wizardState";
	/** @hidden */
	public static final String searchContactNamePropertyName = "searchContactName";
	/** @hidden */
	public static final String searchEmailPropertyName = "searchEmail";
	/** @hidden */
	public static final String candidateContactsPropertyName = "candidateContacts";
	/** @hidden */
	public static final String contactSelectedPropertyName = "contactSelected";
	/** @hidden */
	public static final String inactivePropertyName = "inactive";
	/** @hidden */
	public static final String groupSelectionPropertyName = "groupSelection";
	/** @hidden */
	public static final String groupsExistPropertyName = "groupsExist";
	/** @hidden */
	public static final String newGroupPropertyName = "newGroup";
	/** @hidden */
	public static final String assignedRolesPropertyName = "assignedRoles";
	/** @hidden */
	public static final String activatedPropertyName = "activated";
	/** @hidden */
	public static final String activationCodePropertyName = "activationCode";
	/** @hidden */
	public static final String activationCodeCreationDateTimePropertyName = "activationCodeCreationDateTime";

	/**
	 * Wizard State
	 * <br/>
	 * The create user wizard is staged into the following states which roughly follow in order.
			Either an existing contact is confirmed as that of the new user,
			OR
			A new contact is created for the new user.
			Once the identity of the new user is established, the wizard moves on
			to confirm the new user name and password and membership of groups.
	 **/
	@XmlEnum
	public static enum WizardState implements Enumeration {
		confirmContact("confirmContact", "confirmContact"),
		createContact("createContact", "createContact"),
		confirmUserNameAndPassword("confirmUserNameAndPassword", "confirmUserNameAndPassword"),
		confirmGroupMemberships("confirmGroupMemberships", "confirmGroupMemberships");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private WizardState(String code, String description) {
			this.code = code;
			this.description = description;
			this.domainValue = new DomainValue(code, description);
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toDescription() {
			return description;
		}

		@Override
		public DomainValue toDomainValue() {
			return domainValue;
		}

		public static WizardState fromCode(String code) {
			WizardState result = null;

			for (WizardState value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static WizardState fromDescription(String description) {
			WizardState result = null;

			for (WizardState value : values()) {
				if (value.description.equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				WizardState[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (WizardState value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * admin.user.groupSelection.displayName
	 **/
	@XmlEnum
	public static enum GroupSelection implements Enumeration {
		existingGroups("existingGroups", "Existing groups"),
		newGroup("newGroup", "New group");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private GroupSelection(String code, String description) {
			this.code = code;
			this.description = description;
			this.domainValue = new DomainValue(code, description);
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toDescription() {
			return description;
		}

		@Override
		public DomainValue toDomainValue() {
			return domainValue;
		}

		public static GroupSelection fromCode(String code) {
			GroupSelection result = null;

			for (GroupSelection value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static GroupSelection fromDescription(String description) {
			GroupSelection result = null;

			for (GroupSelection value : values()) {
				if (value.description.equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				GroupSelection[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (GroupSelection value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * admin.user.userName.displayName
	 * <br/>
	 * Length is derived from the maximum email address length from RFC 5321
	 **/
	private String userName;
	/**
	 * admin.user.password.displayName
	 * <br/>
	 * admin.user.password.description
	 **/
	private String password;
	/**
	 * admin.user.generatedPassword.displayName
	 * <br/>
	 * admin.user.generatedPassword.description
	 **/
	private String generatedPassword;
	/**
	 * admin.user.createdDateTime.displayName
	 * <br/>
	 * admin.user.createdDateTime.description
	 **/
	private DateTime createdDateTime;
	/**
	 * admin.user.homeModule.displayName
	 * <br/>
	 * admin.user.homeModule.description
	 **/
	private String homeModule;
	/**
	 * admin.user.newPassword.displayName
	 * <br/>
	 * admin.user.newPassword.description
	 **/
	private String newPassword;
	/**
	 * admin.user.confirmPassword.displayName
	 * <br/>
	 * admin.user.confirmPassword.description
	 **/
	private String confirmPassword;
	/**
	 * admin.user.legacyId.displayName
	 * <br/>
	 * admin.user.legacyId.description
	 **/
	private String legacyId;
	/**
	 * admin.user.passwordExpired.displayName
	 * <br/>
	 * admin.user.passwordExpired.description
	 **/
	private Boolean passwordExpired;
	/**
	 * admin.user.passwordLastChanged.displayName
	 * <br/>
	 * admin.user.passwordLastChanged.description
	 **/
	private DateTime passwordLastChanged;
	/**
	 * admin.user.passwordResetToken.displayName
	 * <br/>
	 * admin.user.passwordResetToken.description
	 * <br/>
	 * This contains a token (UUID + time in millis) which when submitted by the user will enable them to reset their password.
	 **/
	private String passwordResetToken;
	/**
	 * admin.user.passwordHistory.displayName
	 * <br/>
	 * A tab separated list of previous password hashes used
	 **/
	private String passwordHistory;
	/**
	 * admin.user.authenticationFailures.displayName
	 * <br/>
	 * admin.user.authenticationFailures.description
	 * <br/>
	 * This value is zeroed on successful authentication.
	 **/
	private Integer authenticationFailures;
	/**
	 * admin.user.lastAuthenticationFailure.displayName
	 * <br/>
	 * admin.user.lastAuthenticationFailure.description
	 **/
	private Timestamp lastAuthenticationFailure;
	/**
	 * admin.user.association.contact.displayName
	 * <br/>
	 * admin.user.association.contact.description
	 **/
	private Contact contact = null;
	/**
	 * admin.user.association.dataGroup.displayName
	 * <br/>
	 * admin.user.association.dataGroup.description
	 **/
	private DataGroup dataGroup = null;
	/**
	 * admin.user.collection.groups.displayName
	 * <br/>
	 * admin.user.collection.groups.description
	 **/
	private List<GroupExtension> groups = new ChangeTrackingArrayList<>("groups", this);
	/**
	 * admin.user.collection.roles.displayName
	 * <br/>
	 * admin.user.collection.roles.description
	 **/
	private List<UserRole> roles = new ChangeTrackingArrayList<>("roles", this);
	/**
	 * Wizard State
	 * <br/>
	 * The create user wizard is staged into the following states which roughly follow in order.
			Either an existing contact is confirmed as that of the new user,
			OR
			A new contact is created for the new user.
			Once the identity of the new user is established, the wizard moves on
			to confirm the new user name and password and membership of groups.
	 **/
	private WizardState wizardState;
	/**
	 * admin.user.searchContactName.displayName
	 * <br/>
	 * admin.user.searchContactName.description
	 **/
	private String searchContactName;
	/**
	 * admin.user.searchEmail.displayName
	 * <br/>
	 * admin.user.searchEmail.description
	 **/
	private String searchEmail;
	/**
	 * admin.user.collection.candidateContacts.displayName
	 * <br/>
	 * admin.user.collection.candidateContacts.description
	 **/
	private List<UserCandidateContact> candidateContacts = new ChangeTrackingArrayList<>("candidateContacts", this);
	/**
	 * admin.user.contactSelected.displayName
	 **/
	private Boolean contactSelected = new Boolean(false);
	/**
	 * admin.user.inactive.displayName
	 * <br/>
	 * admin.user.inactive.description
	 **/
	private Boolean inactive;
	/**
	 * admin.user.groupSelection.displayName
	 **/
	private GroupSelection groupSelection;
	/**
	 * admin.user.groupsExist.displayName
	 **/
	private Boolean groupsExist;
	/**
	 * admin.user.association.newGroup.displayName
	 **/
	private GroupExtension newGroup = null;
	/**
	 * admin.user.collection.assignedRoles.displayName
	 * <br/>
	 * admin.user.collection.assignedRoles.description
	 **/
	private List<UserRole> assignedRoles = new ArrayList<>();
	/**
	 * Account activated
	 * <br/>
	 * Whether this account has been activated or not. An account not activated means the user has not finished the activation process by clicking the link from their registration email.
	 * <br/>
	 * By default the account will be activated.
			For public users, we want them to activate the account manually so this will be set to false and the activationCode field will be populated.
	 **/
	private Boolean activated = new Boolean(true);
	/**
	 * Activation Code
	 * <br/>
	 * The activation code for this user account.
	 * <br/>
	 * This contains a code which when submitted by the user will activate their account.
	 **/
	private String activationCode;
	/**
	 * The date and time the activation code was created
	 * <br/>
	 * This setting is used to control expiry of activation codes.
	 **/
	private DateTime activationCodeCreationDateTime;

	@Override
	@XmlTransient
	public String getBizModule() {
		return User.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return User.DOCUMENT_NAME;
	}

	public static UserExtension newInstance() {
		try {
			return CORE.getUser().getCustomer().getModule(MODULE_NAME).getDocument(CORE.getUser().getCustomer(), DOCUMENT_NAME).newInstance(CORE.getUser());
		}
		catch (RuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			throw new DomainException(e);
		}
	}

	@Override
	@XmlTransient
	public String getBizKey() {
return modules.admin.User.UserBizlet.bizKey(this);
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof User) && 
					this.getBizId().equals(((User) o).getBizId()));
	}

	/**
	 * {@link #userName} accessor.
	 * @return	The value.
	 **/
	public String getUserName() {
		return userName;
	}

	/**
	 * {@link #userName} mutator.
	 * @param userName	The new value.
	 **/
	@XmlElement
	public void setUserName(String userName) {
		preset(userNamePropertyName, userName);
		this.userName = userName;
	}

	/**
	 * {@link #password} accessor.
	 * @return	The value.
	 **/
	public String getPassword() {
		return password;
	}

	/**
	 * {@link #password} mutator.
	 * @param password	The new value.
	 **/
	@XmlElement
	public void setPassword(String password) {
		preset(passwordPropertyName, password);
		this.password = password;
	}

	/**
	 * {@link #generatedPassword} accessor.
	 * @return	The value.
	 **/
	public String getGeneratedPassword() {
		return generatedPassword;
	}

	/**
	 * {@link #generatedPassword} mutator.
	 * @param generatedPassword	The new value.
	 **/
	@XmlElement
	public void setGeneratedPassword(String generatedPassword) {
		this.generatedPassword = generatedPassword;
	}

	/**
	 * {@link #createdDateTime} accessor.
	 * @return	The value.
	 **/
	public DateTime getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * {@link #createdDateTime} mutator.
	 * @param createdDateTime	The new value.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(DateTimeMapper.class)
	@XmlElement
	public void setCreatedDateTime(DateTime createdDateTime) {
		preset(createdDateTimePropertyName, createdDateTime);
		this.createdDateTime = createdDateTime;
	}

	/**
	 * {@link #homeModule} accessor.
	 * @return	The value.
	 **/
	public String getHomeModule() {
		return homeModule;
	}

	/**
	 * {@link #homeModule} mutator.
	 * @param homeModule	The new value.
	 **/
	@XmlElement
	public void setHomeModule(String homeModule) {
		preset(homeModulePropertyName, homeModule);
		this.homeModule = homeModule;
	}

	/**
	 * {@link #newPassword} accessor.
	 * @return	The value.
	 **/
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * {@link #newPassword} mutator.
	 * @param newPassword	The new value.
	 **/
	@XmlElement
	public void setNewPassword(String newPassword) {
		preset(newPasswordPropertyName, newPassword);
		this.newPassword = newPassword;
	}

	/**
	 * {@link #confirmPassword} accessor.
	 * @return	The value.
	 **/
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * {@link #confirmPassword} mutator.
	 * @param confirmPassword	The new value.
	 **/
	@XmlElement
	public void setConfirmPassword(String confirmPassword) {
		preset(confirmPasswordPropertyName, confirmPassword);
		this.confirmPassword = confirmPassword;
	}

	/**
	 * {@link #legacyId} accessor.
	 * @return	The value.
	 **/
	public String getLegacyId() {
		return legacyId;
	}

	/**
	 * {@link #legacyId} mutator.
	 * @param legacyId	The new value.
	 **/
	@XmlElement
	public void setLegacyId(String legacyId) {
		preset(legacyIdPropertyName, legacyId);
		this.legacyId = legacyId;
	}

	/**
	 * {@link #passwordExpired} accessor.
	 * @return	The value.
	 **/
	public Boolean getPasswordExpired() {
		return passwordExpired;
	}

	/**
	 * {@link #passwordExpired} mutator.
	 * @param passwordExpired	The new value.
	 **/
	@XmlElement
	public void setPasswordExpired(Boolean passwordExpired) {
		preset(passwordExpiredPropertyName, passwordExpired);
		this.passwordExpired = passwordExpired;
	}

	/**
	 * {@link #passwordLastChanged} accessor.
	 * @return	The value.
	 **/
	public DateTime getPasswordLastChanged() {
		return passwordLastChanged;
	}

	/**
	 * {@link #passwordLastChanged} mutator.
	 * @param passwordLastChanged	The new value.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(DateTimeMapper.class)
	@XmlElement
	public void setPasswordLastChanged(DateTime passwordLastChanged) {
		preset(passwordLastChangedPropertyName, passwordLastChanged);
		this.passwordLastChanged = passwordLastChanged;
	}

	/**
	 * {@link #passwordResetToken} accessor.
	 * @return	The value.
	 **/
	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	/**
	 * {@link #passwordResetToken} mutator.
	 * @param passwordResetToken	The new value.
	 **/
	@XmlElement
	public void setPasswordResetToken(String passwordResetToken) {
		preset(passwordResetTokenPropertyName, passwordResetToken);
		this.passwordResetToken = passwordResetToken;
	}

	/**
	 * {@link #passwordHistory} accessor.
	 * @return	The value.
	 **/
	public String getPasswordHistory() {
		return passwordHistory;
	}

	/**
	 * {@link #passwordHistory} mutator.
	 * @param passwordHistory	The new value.
	 **/
	@XmlElement
	public void setPasswordHistory(String passwordHistory) {
		this.passwordHistory = passwordHistory;
	}

	/**
	 * {@link #authenticationFailures} accessor.
	 * @return	The value.
	 **/
	public Integer getAuthenticationFailures() {
		return authenticationFailures;
	}

	/**
	 * {@link #authenticationFailures} mutator.
	 * @param authenticationFailures	The new value.
	 **/
	@XmlElement
	public void setAuthenticationFailures(Integer authenticationFailures) {
		preset(authenticationFailuresPropertyName, authenticationFailures);
		this.authenticationFailures = authenticationFailures;
	}

	/**
	 * {@link #lastAuthenticationFailure} accessor.
	 * @return	The value.
	 **/
	public Timestamp getLastAuthenticationFailure() {
		return lastAuthenticationFailure;
	}

	/**
	 * {@link #lastAuthenticationFailure} mutator.
	 * @param lastAuthenticationFailure	The new value.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(TimestampMapper.class)
	@XmlElement
	public void setLastAuthenticationFailure(Timestamp lastAuthenticationFailure) {
		preset(lastAuthenticationFailurePropertyName, lastAuthenticationFailure);
		this.lastAuthenticationFailure = lastAuthenticationFailure;
	}

	/**
	 * {@link #contact} accessor.
	 * @return	The value.
	 **/
	public Contact getContact() {
		return contact;
	}

	/**
	 * {@link #contact} mutator.
	 * @param contact	The new value.
	 **/
	@XmlElement
	public void setContact(Contact contact) {
		if (this.contact != contact) {
			preset(contactPropertyName, contact);
			this.contact = contact;
		}
	}

	/**
	 * {@link #dataGroup} accessor.
	 * @return	The value.
	 **/
	public DataGroup getDataGroup() {
		return dataGroup;
	}

	/**
	 * {@link #dataGroup} mutator.
	 * @param dataGroup	The new value.
	 **/
	@XmlElement
	public void setDataGroup(DataGroup dataGroup) {
		if (this.dataGroup != dataGroup) {
			preset(dataGroupPropertyName, dataGroup);
			this.dataGroup = dataGroup;
		}
	}

	/**
	 * {@link #groups} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<GroupExtension> getGroups() {
		return groups;
	}

	/**
	 * {@link #groups} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public GroupExtension getGroupsElementById(String bizId) {
		return getElementById(groups, bizId);
	}

	/**
	 * {@link #groups} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setGroupsElementById(String bizId, GroupExtension element) {
		setElementById(groups, element);
	}

	/**
	 * {@link #groups} add.
	 * @param element	The element to add.
	 **/
	public boolean addGroupsElement(GroupExtension element) {
		return groups.add(element);
	}

	/**
	 * {@link #groups} add.
	 * @param index	The index in the list to add the element to.
	 * @param element	The element to add.
	 **/
	public void addGroupsElement(int index, GroupExtension element) {
		groups.add(index, element);
	}

	/**
	 * {@link #groups} remove.
	 * @param element	The element to remove.
	 **/
	public boolean removeGroupsElement(GroupExtension element) {
		return groups.remove(element);
	}

	/**
	 * {@link #groups} remove.
	 * @param index	The index in the list to remove the element from.
	 **/
	public GroupExtension removeGroupsElement(int index) {
		return groups.remove(index);
	}

	/**
	 * {@link #roles} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<UserRole> getRoles() {
		return roles;
	}

	/**
	 * {@link #roles} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public UserRole getRolesElementById(String bizId) {
		return getElementById(roles, bizId);
	}

	/**
	 * {@link #roles} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setRolesElementById(String bizId, UserRole element) {
		setElementById(roles, element);
	}

	/**
	 * {@link #roles} add.
	 * @param element	The element to add.
	 **/
	public boolean addRolesElement(UserRole element) {
		boolean result = roles.add(element);
		element.setParent((UserExtension) this);
		return result;
	}

	/**
	 * {@link #roles} add.
	 * @param index	The index in the list to add the element to.
	 * @param element	The element to add.
	 **/
	public void addRolesElement(int index, UserRole element) {
		roles.add(index, element);
		element.setParent((UserExtension) this);
	}

	/**
	 * {@link #roles} remove.
	 * @param element	The element to remove.
	 **/
	public boolean removeRolesElement(UserRole element) {
		boolean result = roles.remove(element);
		element.setParent(null);
		return result;
	}

	/**
	 * {@link #roles} remove.
	 * @param index	The index in the list to remove the element from.
	 **/
	public UserRole removeRolesElement(int index) {
		UserRole result = roles.remove(index);
		result.setParent(null);
		return result;
	}

	/**
	 * {@link #wizardState} accessor.
	 * @return	The value.
	 **/
	public WizardState getWizardState() {
		return wizardState;
	}

	/**
	 * {@link #wizardState} mutator.
	 * @param wizardState	The new value.
	 **/
	@XmlElement
	public void setWizardState(WizardState wizardState) {
		preset(wizardStatePropertyName, wizardState);
		this.wizardState = wizardState;
	}

	/**
	 * {@link #searchContactName} accessor.
	 * @return	The value.
	 **/
	public String getSearchContactName() {
		return searchContactName;
	}

	/**
	 * {@link #searchContactName} mutator.
	 * @param searchContactName	The new value.
	 **/
	@XmlElement
	public void setSearchContactName(String searchContactName) {
		preset(searchContactNamePropertyName, searchContactName);
		this.searchContactName = searchContactName;
	}

	/**
	 * {@link #searchEmail} accessor.
	 * @return	The value.
	 **/
	public String getSearchEmail() {
		return searchEmail;
	}

	/**
	 * {@link #searchEmail} mutator.
	 * @param searchEmail	The new value.
	 **/
	@XmlElement
	public void setSearchEmail(String searchEmail) {
		preset(searchEmailPropertyName, searchEmail);
		this.searchEmail = searchEmail;
	}

	/**
	 * {@link #candidateContacts} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<UserCandidateContact> getCandidateContacts() {
		return candidateContacts;
	}

	/**
	 * {@link #candidateContacts} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public UserCandidateContact getCandidateContactsElementById(String bizId) {
		return getElementById(candidateContacts, bizId);
	}

	/**
	 * {@link #candidateContacts} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setCandidateContactsElementById(String bizId, UserCandidateContact element) {
		setElementById(candidateContacts, element);
	}

	/**
	 * {@link #candidateContacts} add.
	 * @param element	The element to add.
	 **/
	public boolean addCandidateContactsElement(UserCandidateContact element) {
		boolean result = candidateContacts.add(element);
		element.setParent((UserExtension) this);
		return result;
	}

	/**
	 * {@link #candidateContacts} add.
	 * @param index	The index in the list to add the element to.
	 * @param element	The element to add.
	 **/
	public void addCandidateContactsElement(int index, UserCandidateContact element) {
		candidateContacts.add(index, element);
		element.setParent((UserExtension) this);
	}

	/**
	 * {@link #candidateContacts} remove.
	 * @param element	The element to remove.
	 **/
	public boolean removeCandidateContactsElement(UserCandidateContact element) {
		boolean result = candidateContacts.remove(element);
		element.setParent(null);
		return result;
	}

	/**
	 * {@link #candidateContacts} remove.
	 * @param index	The index in the list to remove the element from.
	 **/
	public UserCandidateContact removeCandidateContactsElement(int index) {
		UserCandidateContact result = candidateContacts.remove(index);
		result.setParent(null);
		return result;
	}

	/**
	 * {@link #contactSelected} accessor.
	 * @return	The value.
	 **/
	public Boolean getContactSelected() {
		return contactSelected;
	}

	/**
	 * {@link #contactSelected} mutator.
	 * @param contactSelected	The new value.
	 **/
	@XmlElement
	public void setContactSelected(Boolean contactSelected) {
		preset(contactSelectedPropertyName, contactSelected);
		this.contactSelected = contactSelected;
	}

	/**
	 * {@link #inactive} accessor.
	 * @return	The value.
	 **/
	public Boolean getInactive() {
		return inactive;
	}

	/**
	 * {@link #inactive} mutator.
	 * @param inactive	The new value.
	 **/
	@XmlElement
	public void setInactive(Boolean inactive) {
		preset(inactivePropertyName, inactive);
		this.inactive = inactive;
	}

	/**
	 * {@link #groupSelection} accessor.
	 * @return	The value.
	 **/
	public GroupSelection getGroupSelection() {
		return groupSelection;
	}

	/**
	 * {@link #groupSelection} mutator.
	 * @param groupSelection	The new value.
	 **/
	@XmlElement
	public void setGroupSelection(GroupSelection groupSelection) {
		this.groupSelection = groupSelection;
	}

	/**
	 * {@link #groupsExist} accessor.
	 * @return	The value.
	 **/
	public Boolean getGroupsExist() {
		return groupsExist;
	}

	/**
	 * {@link #groupsExist} mutator.
	 * @param groupsExist	The new value.
	 **/
	@XmlElement
	public void setGroupsExist(Boolean groupsExist) {
		this.groupsExist = groupsExist;
	}

	/**
	 * {@link #newGroup} accessor.
	 * @return	The value.
	 **/
	public GroupExtension getNewGroup() {
		return newGroup;
	}

	/**
	 * {@link #newGroup} mutator.
	 * @param newGroup	The new value.
	 **/
	@XmlElement
	public void setNewGroup(GroupExtension newGroup) {
		if (this.newGroup != newGroup) {
			this.newGroup = newGroup;
		}
	}

	/**
	 * {@link #assignedRoles} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<UserRole> getAssignedRoles() {
		return assignedRoles;
	}

	/**
	 * {@link #assignedRoles} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public UserRole getAssignedRolesElementById(String bizId) {
		return getElementById(assignedRoles, bizId);
	}

	/**
	 * {@link #assignedRoles} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setAssignedRolesElementById(String bizId, UserRole element) {
		setElementById(assignedRoles, element);
	}

	/**
	 * {@link #assignedRoles} add.
	 * @param element	The element to add.
	 **/
	public boolean addAssignedRolesElement(UserRole element) {
		return assignedRoles.add(element);
	}

	/**
	 * {@link #assignedRoles} add.
	 * @param index	The index in the list to add the element to.
	 * @param element	The element to add.
	 **/
	public void addAssignedRolesElement(int index, UserRole element) {
		assignedRoles.add(index, element);
	}

	/**
	 * {@link #assignedRoles} remove.
	 * @param element	The element to remove.
	 **/
	public boolean removeAssignedRolesElement(UserRole element) {
		return assignedRoles.remove(element);
	}

	/**
	 * {@link #assignedRoles} remove.
	 * @param index	The index in the list to remove the element from.
	 **/
	public UserRole removeAssignedRolesElement(int index) {
		return assignedRoles.remove(index);
	}

	/**
	 * {@link #activated} accessor.
	 * @return	The value.
	 **/
	public Boolean getActivated() {
		return activated;
	}

	/**
	 * {@link #activated} mutator.
	 * @param activated	The new value.
	 **/
	@XmlElement
	public void setActivated(Boolean activated) {
		preset(activatedPropertyName, activated);
		this.activated = activated;
	}

	/**
	 * {@link #activationCode} accessor.
	 * @return	The value.
	 **/
	public String getActivationCode() {
		return activationCode;
	}

	/**
	 * {@link #activationCode} mutator.
	 * @param activationCode	The new value.
	 **/
	@XmlElement
	public void setActivationCode(String activationCode) {
		preset(activationCodePropertyName, activationCode);
		this.activationCode = activationCode;
	}

	/**
	 * {@link #activationCodeCreationDateTime} accessor.
	 * @return	The value.
	 **/
	public DateTime getActivationCodeCreationDateTime() {
		return activationCodeCreationDateTime;
	}

	/**
	 * {@link #activationCodeCreationDateTime} mutator.
	 * @param activationCodeCreationDateTime	The new value.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(DateTimeMapper.class)
	@XmlElement
	public void setActivationCodeCreationDateTime(DateTime activationCodeCreationDateTime) {
		preset(activationCodeCreationDateTimePropertyName, activationCodeCreationDateTime);
		this.activationCodeCreationDateTime = activationCodeCreationDateTime;
	}

	/**
	 * Candidate Contacts is empty
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isCandidateContactsEmpty() {
		return (candidateContacts.isEmpty());
	}

	/**
	 * {@link #isCandidateContactsEmpty} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotCandidateContactsEmpty() {
		return (! isCandidateContactsEmpty());
	}

	/**
	 * Confirm Contact step
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isConfirmContact() {
		return (WizardState.confirmContact.equals(getWizardState()));
	}

	/**
	 * {@link #isConfirmContact} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotConfirmContact() {
		return (! isConfirmContact());
	}

	/**
	 * Confirm Group Memberships step
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isConfirmGroupMemberships() {
		return (WizardState.confirmGroupMemberships.equals(getWizardState()));
	}

	/**
	 * {@link #isConfirmGroupMemberships} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotConfirmGroupMemberships() {
		return (! isConfirmGroupMemberships());
	}

	/**
	 * Confirm User Name and Password step
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isConfirmUserNameAndPassword() {
		return (WizardState.confirmUserNameAndPassword.equals(getWizardState()));
	}

	/**
	 * {@link #isConfirmUserNameAndPassword} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotConfirmUserNameAndPassword() {
		return (! isConfirmUserNameAndPassword());
	}

	/**
	 * Create Contact step
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isCreateContact() {
		return (WizardState.createContact.equals(getWizardState()));
	}

	/**
	 * {@link #isCreateContact} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotCreateContact() {
		return (! isCreateContact());
	}

	/**
	 * Created
	 *
	 * @return The condition
	 */
	@XmlTransient
	@Override
	public boolean isCreated() {
		return (isPersisted());
	}

	/**
	 * {@link #isCreated} negation.
	 *
	 * @return The negated condition
	 */
	@Override
	public boolean isNotCreated() {
		return (! isCreated());
	}

	/**
	 * Designer
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isDesigner() {
		return (isUserInRole("design", "BizHubDesigner"));
	}

	/**
	 * {@link #isDesigner} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotDesigner() {
		return (! isDesigner());
	}

	/**
	 * In Data Group
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isInDataGroup() {
		return (! isUserInDataGroup(null));
	}

	/**
	 * {@link #isInDataGroup} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotInDataGroup() {
		return (! isInDataGroup());
	}

	/**
	 * Security Administrator
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isSecurityAdministrator() {
		return (isUserInRole("admin","SecurityAdministrator"));
	}

	/**
	 * {@link #isSecurityAdministrator} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotSecurityAdministrator() {
		return (! isSecurityAdministrator());
	}

	/**
	 * showExistingGroups
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isShowExistingGroups() {
		return (Boolean.TRUE.equals(groupsExist));
	}

	/**
	 * {@link #isShowExistingGroups} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotShowExistingGroups() {
		return (! isShowExistingGroups());
	}

	/**
	 * showGroupCreator
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isShowGroupCreator() {
		return (GroupSelection.newGroup.equals(groupSelection));
	}

	/**
	 * {@link #isShowGroupCreator} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotShowGroupCreator() {
		return (! isShowGroupCreator());
	}

	/**
	 * Show Next Button
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isShowNextButton() {
		return (isCreateContact() || isConfirmUserNameAndPassword());
	}

	/**
	 * {@link #isShowNextButton} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotShowNextButton() {
		return (! isShowNextButton());
	}
}
