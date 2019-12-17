$(function() {
	// Menu toggle script
	$("#menu-toggle").click(function(e) {
		e.preventDefault();
		$("#wrapper").toggleClass("menuDisplayed");
	});

	$('#copyrightYear').html(new Date().getFullYear());

	$(".app-v-nav > li > a")
			.not(":last, #back-btn")
			.click(
					function() {
						$(".app-v-nav > li > a").not(":last, #back-btn")
								.parent().removeClass("active");
						$("#page-content-wrapper > div.content:visible")
								.toggleClass("d-none");
						$(this).parent().toggleClass("active");
						fetchTabContent($(this).attr('href'), false);
						$($(this).attr('href')).removeClass("d-none");

						$("#mtgsearch")
								.keyup(
										function() {
											var $this = $(this);
											console.log("Test");
											if ($this.val().length >= 1) {
												$
														.get(
																getAppContext()
																		+ "meeting/find/"
																		+ $this
																				.val(),
																function(data) {
																	$(
																			"#resultsDiv")
																			.html(
																					data);
																	$(
																			".mtg-application-btn")
																			.click(
																					function(
																							event) {
																						event
																								.preventDefault();
																						var $this = $(this);
																						$
																								.post(
																										getAppContext()
																												+ "application/"
																												+ $this
																														.siblings()[0].value,
																										function(
																												data) {
																											$(
																													".confirmation-alert")
																													.html(
																															data);
																											// Reset
																											// search
																											// field
																											$(
																													"#mtgsearch")
																													.val(
																															'');
																											setTimeout(
																													function() {
																														$(
																																".confirmation-alert")
																																.fadeOut(
																																		1500);
																													},
																													4000);

																										});
																						// var
																						// test
																						// =
																						// $(".mtg-application-btn:first").;
																					});
																});
											}
										});

					});

	$('[data-toggle="tooltip"]').tooltip();

});

function fetchTabContent(tabId, reload) {
	console.log(tabId);
	switch (tabId) {
	case "#profile-tab":
		break;
	case "#teams-tab":
		$.get(getAppContext() + "memberships", function(data) {
			console.log("A");
			$(tabId).html(data);
			$("#teamForm").submit(
					function(event) {
						event.preventDefault();
						var $this = $(this);
						$("#createTeambtn").attr("disabled", true);
						$.post(getAppContext() + "meeting", $this.serialize(),
								function(data) {
									$("#newTeamModal").modal('hide');
									$('.modal-backdrop').remove();
									fetchTabContent("#teams-tab", true);
									$(".confirmation-alert").html(data);
									setTimeout(function() {
										$(".confirmation-alert").fadeOut(1500);
									}, 4000);

								});
					});
		});
		break;
	case "#applications-tab":
		break;
	case "#notifications-tab":
		break;
	case "#dashboard-tab":
		break;
	case "#accounts-tab":
		getAccountDetails("#checking-acct", "CHK", 1);
		break;
	case "#roster-tab":
		break;
	}
}

function getAppContext() {
	var loc = window.location;
	return '/' + loc.pathname.split('/')[1] + '/';
}

$('#acct-navbar a').on('click', function(e) {
	e.preventDefault();
	var href = $(this).attr('href');
	switch (href) {
	case "#checking-acct":
		getAccountDetails(href, "CHK", 1);
		break;
	case "#savings-acct":
		getAccountDetails(href, "SAV", 2);
		break;
	case "#loans-acct":
		getAccountDetails(href, "LOA", 3);
		break;
	case "#investment-acct":
		getAccountDetails(href, "INV", 4);
		break;
	}
	$(this).tab('show');
});

function registerTransactionMenu() {
	$('#transaction-menu a').on('click', function(e) {
		e.preventDefault();
		var id = $(this).attr('id');
		var modalId = "#transactionModal";
		var classList = $(this).attr('class').split(/\s+/);
		$.each(classList, function(index, item) {
			if (item === 'dropdown-item') {
				return true;
			} else if (item === 'dep') {
				getTransactionForm(id, item, modalId);
			} else if (item === 'con') {
				getTransactionForm(id, item, modalId);
			} else if (item === 'sav') {
				getTransactionForm(id, item, modalId);
			} else {
				getTransactionForm(id, item, modalId);
			}
		});

	});
}

function getTransactionForm(actId, transType, modalId) {
	$.get(getAppContext() + "Accounts/Transact/" + actId + "/" + transType,
			function(data) {
				$(modalId).html(data);
				$(modalId).modal('show');
				submitTransaction(modalId);
			});
}

function getAccountDetails(id, actType, chart) {
	$.get(getAppContext() + "Accounts/" + actType, function(data) {
		$(id).html(data);
		getCashFlowOVWChart(chart);
		registerTransactionMenu();
	});
}

function submitTransaction(modalId) {
	$('#trs-form-footer a').on(
			'click',
			function(e) {
				e.preventDefault();
				var href = $(this).attr('href');
				console.log(href);
				$.post(getAppContext() + "Accounts/" + href, $(
						"#transactionForm").serialize(), function(data) {
					$(modalId).modal('hide');
					$('.modal-backdrop').remove();
					$(".confirmation-alert").html("");
					$(".confirmation-alert").removeAttr("style");
					$(".confirmation-alert").html(data);
					setTimeout(function() {
						$(".confirmation-alert").fadeOut(1500);
					}, 4000);

				});
			});
}