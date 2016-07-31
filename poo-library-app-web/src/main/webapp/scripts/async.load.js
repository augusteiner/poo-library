
function queued(selector, options, dfd) {

  var queue = [];
  var last = $.Deferred().done(function() {
    queue.shift();
  });

  $(selector).each(function() {

    var $this = $(this);

    queue.push($.Deferred().done(function() {

      $this.bind('load', function() {

        if (queue.length > 0) {

          //console.log('removing!');

          last.notify();

          var dfd = queue.shift();
          dfd.resolve();
        }
      });

      $this.prop('src', $this.data('src'));

    }));
  });

  queue.push(last);

  var dfd = queue.shift();
  dfd.resolve();

  return last;
}
function bulk(elements) {

  var dfd = $.Deferred();

  var els = $(elements);
  var total = els.length;

  els.each(function() {

    queued(this).done(function(){
      total--;
      dfd.notify();

      if (total == 0) {
        dfd.resolve();
      }
    });
  });

  return dfd.promise();
}
function bootstrap() {

  var appName = 'biblioteca';

  queued('script.queued[data-src]').done(function() {

    bulk('script.pass-1[data-src]').done(function() {

      $(document).prop('ng-app', appName);

      angular.bootstrap(document, [appName]);
    });
  });
}

