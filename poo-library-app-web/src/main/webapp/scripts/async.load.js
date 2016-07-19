
function queued(selector, options, dfd) {

  var queue = [];
  var last = $.Deferred().done(function() {
    queue.shift();
  });

  $(selector).each(function() {

    var $this = $(this);

    queue.push($.Deferred().done(function() {

      $this.load(function() {

        if (queue.length > 0) {

          //console.log('removing!');

          last.notify();

          queue.shift().resolve();
        }
      });

      $this.prop('src', $this.data('src'));

    }));
  });

  queue.push(last);

  queue.shift().resolve();

  return last;
}
function bulk(selector) {

  var list = [];
  var dfd = $.Deferred();

  $(selector).each(function() {

    var $this = $(this);

    list.push(1);

    $this.load(function() {

      list.shift();

      if (list.length == 0) {

        dfd.resolve();
      }
    });

    $this.prop('src', $this.data('src'));
  });

  return dfd;
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
