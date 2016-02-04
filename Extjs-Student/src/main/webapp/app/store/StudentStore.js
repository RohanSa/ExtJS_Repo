Ext.define('app.store.StudentStore', {

			extend : 'Ext.data.Store',

			model : 'app.model.Student',

			proxy : {
				type : "ajax",
				url : 'student/teststudent'

			}

		});