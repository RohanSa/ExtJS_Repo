Ext.define('app.store.UserProfileStore', {

			extend : 'Ext.data.Store',

			model : 'app.model.UserProfileDTO',

			proxy : {

				type : 'ajax',
				url : 'data/menudata.json',
				reader : {

					type : 'json',
					root : 'data'
				}
			}

		});