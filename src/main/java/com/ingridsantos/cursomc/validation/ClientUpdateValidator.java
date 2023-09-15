package com.ingridsantos.cursomc.validation;

import com.ingridsantos.cursomc.dto.ClienteDTO;
import com.ingridsantos.cursomc.model.Cliente;
import com.ingridsantos.cursomc.repository.ClienteRepository;
import com.ingridsantos.cursomc.util.FieldMessage;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClienteDTO> {

    private ClienteRepository clienteRepository;
    private HttpServletRequest request;

    public ClientUpdateValidator(ClienteRepository clienteRepository, HttpServletRequest request) {
        this.clienteRepository = clienteRepository;
        this.request = request;
    }
    @Override
    public void initialize(ClientUpdate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> lista = new ArrayList<>();

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)){
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
