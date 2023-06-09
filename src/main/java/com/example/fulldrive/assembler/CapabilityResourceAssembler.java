package com.example.fulldrive.assembler;

import com.example.fulldrive.controllers.CapabilityController;
import com.example.fulldrive.entity.Capability;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CapabilityResourceAssembler implements RepresentationModelAssembler<Capability, EntityModel<Capability>> {
    @Override
    public EntityModel<Capability> toModel(Capability entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(CapabilityController.class).getCapability(entity.getId())).withRel("get_this_cap"),
                linkTo(methodOn(CapabilityController.class).deleteCapability(entity.getId())).withRel("delete_this_cap"),
                linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel("get_all_caps"),
                Link.of("http://localhost:8080/dashboard/" + entity.getId(), "update_this_cap"));
    }
}
