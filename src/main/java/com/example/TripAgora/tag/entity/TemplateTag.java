package com.example.TripAgora.tag.entity;

import com.example.TripAgora.template.entity.Template;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "template_tag")
public class TemplateTag {
    @EmbeddedId
    private TemplateTagId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("templateId")
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    private TemplateTag(Template template, Tag tag) {
        this.template = template;
        this.tag = tag;
        this.id = new TemplateTagId(template.getId(), tag.getId());
    }

    public static TemplateTag of(Template template, Tag tag) {
        return new TemplateTag(template, tag);
    }
}