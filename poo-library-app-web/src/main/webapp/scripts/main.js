
(function() {

  function queued(e,n,r){var t=[],o=$.Deferred().done(function(){t.shift()});
  return $(e).each(function(){var e=$(this);t.push($.Deferred().done(function(){
  e.load(function(){t.length>0&&(o.notify(),t.shift().resolve())}),e.prop("src",
  e.data("src"))}))}),t.push(o),t.shift().resolve(),o}function bulk(e){var n=
  $.Deferred(),r=$(e),t=r.length;return r.each(function(){queued(this).done(
  function(){t--,n.notify(),0==t&&n.resolve()})}),n.promise()}

  if (jQuery)
    $(bootstrap);
  else
    document.getElementById('jquery').addEventListener('load', bootstrap);

  function bootstrap() {

    var APP_NAME = 'biblioteca';

    var progress = $('progress.main-progress');
    var scripts = $('script.queued');
    var total = scripts.length;

    progress.prop('max', total);

    var p = function() {
      progress.val(progress.val() + 1);
    };

    $('.navbar-collapse a:not(.dropdown-toggle)').click(function(){
      $(".navbar-collapse").collapse('hide');
    });

    var done = function() {
      $(document).prop('ng-app', APP_NAME);
      angular.bootstrap(document, [APP_NAME]);
    };

    bulk('.bulk-1').progress(p).done(function() {
      bulk('.bulk-2').progress(p).done(function() {
        bulk('.bulk-3').progress(p).done(function() {
          bulk('.bulk-4').progress(p).done(function() {
            bulk('.bulk-5').progress(p).done(done);
          });
        });
      });
    });
  }
})();

