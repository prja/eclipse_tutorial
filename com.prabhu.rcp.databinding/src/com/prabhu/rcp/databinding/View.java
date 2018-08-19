package com.prabhu.rcp.databinding;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.prabhu.rcp.databinding.model.Address;
import com.prabhu.rcp.databinding.model.Employee;



public class View extends ViewPart {
	public static final String ID = "com.prabhu.rcp.databinding.view";

	private Employee emp;

	private Text firstName;
	private Text ageText;
	private Button permanentBtn;
	private Combo departmentCombo;
	private Text countryText;

	@Override
	public void createPartControl(Composite parent) {

		emp = createEmp();
		// Lets put thing to order
		GridLayout layout = new GridLayout(2, false);
		layout.marginRight = 5;
		parent.setLayout(layout);

		Label firstLabel = new Label(parent, SWT.NONE);
		firstLabel.setText("Firstname: ");
		firstName = new Text(parent, SWT.BORDER);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		firstName.setLayoutData(gridData);

		Label ageLabel = new Label(parent, SWT.NONE);
		ageLabel.setText("Sallary: ");
		ageText = new Text(parent, SWT.BORDER);

		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		ageText.setLayoutData(gridData);

		Label marriedLabel = new Label(parent, SWT.NONE);
		marriedLabel.setText("Permanent: ");
		permanentBtn = new Button(parent, SWT.CHECK);

		Label genderLabel = new Label(parent, SWT.NONE);
		genderLabel.setText("Department: ");
		departmentCombo = new Combo(parent, SWT.NONE);
		departmentCombo.add("IT");
		departmentCombo.add("NonIT");

		Label countryLabel = new Label(parent, SWT.NONE);
		countryLabel.setText("Country");
		countryText = new Text(parent, SWT.BORDER);

		Button button1 = new Button(parent, SWT.PUSH);
		button1.setText("print model");
		button1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Firstname: " + emp.getFirstName());
				System.out.println("Sallary " + emp.getSallary());
				System.out.println("Permanent: " + emp.isPermanent());
				System.out.println("Department: " + emp.getDepartment());
				System.out.println("Country: "
						+ emp.getAddress().getCountry());
			}
		});

		Button button2 = new Button(parent, SWT.PUSH);
		button2.setText("Update model");
		button2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				emp.setFirstName("Jyoti");
				emp.setSallary(emp.getSallary() + 10000);
				emp.setPermanent(!emp.isPermanent());
				if (emp.getDepartment().equals("IT")) {

				} else {
					emp.setDepartment("IT");
				}
				if (emp.getAddress().getCountry().equals("India")) {
					emp.getAddress().setCountry("USA");
				} else {
					emp.getAddress().setCountry("India");
				}
			}
		});

		// Now lets do the binding
		bindValues();
	}

	private Employee createEmp() {
		Employee emp = new Employee();
		Address address = new Address();
		address.setCountry("India");
		emp.setAddress(address);
		emp.setFirstName("Prabhu");
		emp.setLastName("Narayan");
		emp.setDepartment("IT");
		emp.setPermanent(true);
		
		return emp;
	}

	@Override
	public void setFocus() {
	}

	private void bindValues() {
		// The DataBindingContext object will manage the databindings
		// Lets bind it
		DataBindingContext ctx = new DataBindingContext();
		IObservableValue widgetValue = WidgetProperties.text(SWT.Modify)
				.observe(firstName);
		IObservableValue modelValue = BeanProperties.value(Employee.class,
				"firstName").observe(emp);
		ctx.bindValue(widgetValue, modelValue);

		// Bind the age including a validator
		widgetValue = WidgetProperties.text(SWT.Modify).observe(ageText);
		modelValue = BeanProperties.value(Employee.class, "sallary").observe(emp);
		// Add an validator so that age can only be a number
		IValidator validator = new IValidator() {
			@Override
			public IStatus validate(Object value) {
				if (value instanceof Integer) {
					String s = String.valueOf(value);
					if (s.matches("\\d*")) {
						return ValidationStatus.ok();
					}
				}
				return ValidationStatus.error("Not a number");
			}
		};

		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setBeforeSetValidator(validator);

		Binding bindValue = ctx.bindValue(widgetValue, modelValue, strategy,
				null);
		// Add some decorations
		ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);

		widgetValue = WidgetProperties.selection().observe(permanentBtn);
		modelValue = BeanProperties.value(Employee.class, "permanent").observe(
				emp);
		ctx.bindValue(widgetValue, modelValue);

		widgetValue = WidgetProperties.selection().observe(departmentCombo);
		modelValue = BeansObservables.observeValue(emp, "department");

		ctx.bindValue(widgetValue, modelValue);

		// Address field is bound to the Ui
		widgetValue = WidgetProperties.text(SWT.Modify).observe(countryText);

		modelValue = BeanProperties.value(Employee.class, "address.country")
				.observe(emp);

		ctx.bindValue(widgetValue, modelValue);

	}

	public static void addDirtyListenersToContext(DataBindingContext context,
			IChangeListener listener) {
		IObservableList bindings = context.getBindings();
		for (Object o : bindings) {
			Binding b = (Binding) o;
			b.getTarget().addChangeListener(listener);
		}
	}
}
