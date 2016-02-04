<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
<script type="text/javascript" src="extjs/js/ext-all.js"></script>
<link rel="stylesheet" type="text/css" href="extjs/css/ext-all.css">

<script type="text/javascript">
	Ext.onReady(function() {

		var store = Ext.create('Ext.data.Store', {
			fields : [ 'id', 'name' ],
			proxy : {
				type : 'ajax',
				url : 'student/getstudentname.spring', // whatever your webservice path is
				reader : { type : 'json'
					      , root : 'data'}
			},
			autoLoad : true
		});

		var combo = new Ext.form.ComboBox({
			id : 'myCombo',
			fieldLabel : 'Student Name',
			valueField : 'id',
			displayField : 'name',
			store : store,
			queryMode : 'local',
			typeAhead : true,
			forceSelection : true,
			allowBlank : false,
			anchor : '100%'
		});

		var stateForm = new Ext.FormPanel({
			frame : true,
			url : 'saveState.json',
			title : 'Combo Box Example',
			bodyStyle : 'padding:5px 5px 0',
			width : 250,
			labelAlign : 'top',
			layout : 'form',
			items : [ combo ]
		});
		stateForm.render(document.body);

	});
</script>
</head>
<body>

</body>
</html>
