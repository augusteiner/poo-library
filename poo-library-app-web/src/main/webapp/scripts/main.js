
(function() {

  function queued(e,n,r){var t=[],o=$.Deferred().done(function(){t.shift()});
  $(e).each(function(){var e=$(this);t.push($.Deferred().done(function(){
  e.bind("load",function(){if(t.length>0){o.notify();var e=t.shift();e.resolve()}}),
  e.prop("src",e.data("src"))}))}),t.push(o);var r=t.shift();return r.resolve(),o}
  function bulk(e){var n=$.Deferred(),r=$(e),t=r.length;return r.each(function(){
  queued(this).done(function(){t--,n.notify(),0==t&&n.resolve()})}),n.promise()}

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

    for (var i = 5; i >= 1; i--) {

      done = (function(bulkName, done) {

        return function() {

          bulk(bulkName).progress(p).done(done);
        };

      })('.bulk-' + i, done);
    }

    done();
  }
})();

