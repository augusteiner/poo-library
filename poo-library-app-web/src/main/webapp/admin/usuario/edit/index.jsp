<script type="text/javascript" src="assets/jquery.inputmask/dist/jquery.inputmask.bundle.min.js" defer async></script>
<script type="text/javascript">
$(function(){
  $('.cpf-input').inputmask(
      '999.999.999-99',
      { autoUnmask : true,
        placeholder: '',
        showMaskOnHover: false,
        showMaskOnFocus: false, });
});
</script>
<div class="well">
  <form class="form-horizontal" action="javascript:void(0);"
    method="post" ng-submit="save()" ng-controller="AdminCtrlr"
    ng-init="edit(params.id)">
    <fieldset>
      <legend>Cadastro de Usuário</legend>
      <input type="hidden" name="id" ng-model="data.id">
      <div class="form-group">
        <div class="col-lg-12">
          <label class="control-label"> Nome </label> <input
            class="form-control" name="nome" type="text"
            ng-model="data.nome" required autofocus>
        </div>
      </div>
      <div class="form-group">
        <div class="col-lg-12">
          <label class="control-label">CPF</label>
          <input class="form-control cpf-input" name="cpf" type="text" maxlength="14" ng-model="data.cpf" required> 
        </div>
      </div>
      <!-- <div> -->
      <!--   <label> Tipo:<select ng-model="data.tipo"> -->
      <!--       <option value="" selected disabled hidden>-- -->
      <!--         SELECIONE O TIPO --</option> -->
      <!--       <option value="COMUM">Usuário Comum</option> -->
      <!--       <option value="ADMIN">Administrador</option> -->
      <!--   </select> -->
      <!--   </label> -->
      <!-- </div> -->
      <div class="form-group">
        <div class="col-lg-12">
          <label class="control-label"> Endereço</label> <input
            class="form-control" ng-model="data.endereco">
        </div>
      </div>
      <div class="form-group">
        <div class="col-lg-12 modal-footer">
          <button class="btn btn-sm btn-default" type="reset"
            ng-click="cancel()">
            <span class="glyphicon glyphicon-chevron-left"></span>
            Voltar
          </button>
          <!-- <button class="btn btn-warning" type="reset" -->
          <!--   ng-click="void()"> -->
          <!--   <span class="glyphicon glyphicon-trash"></span> Limpar -->
          <!-- </button> -->
          <button class="btn btn-sm btn-primary" type="submit">
            <span class="glyphicon glyphicon-floppy-disk"></span> Salvar
          </button>
        </div>
      </div>
    </fieldset>
  </form>
</div>