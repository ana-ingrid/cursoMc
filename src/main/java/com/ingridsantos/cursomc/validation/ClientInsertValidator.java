package com.ingridsantos.cursomc.validation;

import com.ingridsantos.cursomc.dto.SalvaClienteDTO;
import com.ingridsantos.cursomc.enums.TipoCliente;
import com.ingridsantos.cursomc.model.Cliente;
import com.ingridsantos.cursomc.repository.ClienteRepository;
import com.ingridsantos.cursomc.util.FieldMessage;
import com.ingridsantos.cursomc.validation.util.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
public class ClientInsertValidator implements ConstraintValidator<ClientInsert,SalvaClienteDTO> {

    private ClienteRepository clienteRepository;

    public ClientInsertValidator(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void initialize(ClientInsert ann) {
    }
    @Override
    public boolean isValid(SalvaClienteDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> lista = new ArrayList<>();

        if (objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            lista.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            lista.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if (aux != null){
            lista.add(new FieldMessage("email", "email já existente"));
        }

        for (FieldMessage e : lista) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return lista.isEmpty();
    }
}