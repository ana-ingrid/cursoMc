package com.ingridsantos.cursomc.validation;

import com.ingridsantos.cursomc.dto.SalvaClienteDTO;
import com.ingridsantos.cursomc.enums.TipoCliente;
import com.ingridsantos.cursomc.util.FieldMessage;
import com.ingridsantos.cursomc.validation.util.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
public class ClienteInsertValidator implements ConstraintValidator<ClientInsert,SalvaClienteDTO> {
    @Override
    public void initialize(ClientInsert ann) {
    }
    @Override
    public boolean isValid(SalvaClienteDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}