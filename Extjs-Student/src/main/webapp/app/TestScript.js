Ext.onReady(function() {

			var store = new Ext.data.Store({
						proxy : new Ext.data.HttpProxy({
									url : 'student/getstudentname.spring'
								}),
						reader : new Ext.data.JsonReader({
									root : 'data'
								}, [{
											name : 'id'
										}, {
											name : 'name'
										}])

					});

			var combo = new Ext.form.ComboBox({
						id : 'StudentCombo',
						store : store,
						displayField : 'name',
						valueField : 'id',
						hiddenName : 'codeId',
						typeAhead : true,
						mode : 'local',
						fieldLabel : 'Student name',
						anchor : '100%',
						forceSelection : true,
						triggerAction : 'all',
						emptyText : 'Select a naeme...',
						selectOnFocus : true
					});

			var stateForm = new Ext.FormPanel({
						frame : true,
						url : 'saveState.json',
						title : 'Combo Box Example',
						bodyStyle : 'padding:5px 5px 0',
						width : 250,
						labelAlign : 'top',
						layout : 'form',
						items : [combo]
					});

			store.load();

			stateForm.render(document.body);

		});
