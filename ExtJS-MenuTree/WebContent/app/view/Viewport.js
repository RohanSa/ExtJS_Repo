Ext.define('app.view.Viewport', {

			extend : 'Ext.container.Viewport',

			requires : ['Ext.tab.Panel', 'Ext.window.MessageBox'],

			layout : 'fit',

			initComponent : function() {

				var me = this;

				var menutree = me.buildMenuTree();

				this.items = [{
							xtype : 'panel',
							layout : 'fit',
							items : {
								xtype : 'tabpanel',
								itemId : 'maintabs',
								border : true,
								hidden : false
							}

						}]

			},

			buildMenuTree : function() {

				//var menutree;

				var userprofilestore = Ext.create('app.store.UserProfileStore');
				var treepanel = Ext.create('Ext.tree.Panel',{
				rootVisible : false,
				collapsible : true
					
				});

				userprofilestore.load({

							callback : function(records, operation, success) {
								if (success == true) {
									var menudata = userprofilestore.getAt(0);
									var node;
									menudata.fields.each(function(field){
										console.log(field.name+ ' ' +field.value)
										if(field.name == 'fileuploadflg' && menudata.get(field.name)=='Y')
										{
											node =treepanel.getRootNode();
											console.log(node);
											
										}
										
										
									});
									
									
									

								}

							}
						});

			}

		});