(function () {
  'use strict';

  angular
    .module('portalMarketplace')
    .controller('SecurityCtrl', [SecurityCtrl]);

  function SecurityCtrl() {

    var security = this;
    var roles = [];
    var role = 'admin';
    roles[role] = {description:'Perfil de administrador'};

    security.user = {roles:roles};

    //Actions
    security.hasRole = hasRole;

    function hasRole(role) {

      if (!security.user || !security.user.roles[role]) {
          return false;
      }
      return security.user.roles[role];
    }
  }
})();
