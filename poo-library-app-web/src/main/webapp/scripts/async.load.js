
function queued(selector, options) {

  var queue = [];

  $(selector).each(function() {

    var $this = $(this);

    queue.push($.Deferred().done(function() {

      $this.load(function() {

        if (queue.length > 0) {

          console.log('removing!');

          queue.shift().resolve();
        }
      });

      $this.prop('src', $this.data('src'));

    }));
  });

  queue.push($.Deferred().done(function(){
    queue.shift();
  }));

  queue.shift().resolve();

  return queue[queue.length - 1];
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
