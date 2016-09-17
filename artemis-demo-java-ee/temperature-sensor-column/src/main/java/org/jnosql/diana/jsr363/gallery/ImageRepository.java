package org.jnosql.diana.jsr363.gallery;


import org.jnosql.artemis.column.ColumnCrudOperation;
import org.jnosql.diana.api.Value;
import org.jnosql.diana.api.column.Column;
import org.jnosql.diana.api.column.ColumnCondition;
import org.jnosql.diana.api.column.ColumnQuery;
import org.jnosql.diana.api.key.BucketManager;
import org.jnosql.diana.api.key.KeyValueEntity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class ImageRepository {

    private static final Logger LOGGER = Logger.getLogger(ImageRepository.class.getName());
    public static final String GALLERY = "gallery";

    @Inject
    private ColumnCrudOperation crudOperation;

    @Inject
    private BucketManager bucketManager;

    @PostConstruct
    void init() {
        LOGGER.info("Starting to load all galary imagesId");
        Optional<Gallery> gallery = crudOperation.singleResult(getFindByIdQuery());
        List<String> imagesId = gallery.get().getImages();
        LOGGER.info("Gallary loaded, loading the imagesId" + imagesId);
        ColumnQuery query = ColumnQuery.of("image");
        query.addCondition(ColumnCondition.in(Column.of("name", imagesId)));
        List<Image> images = crudOperation.find(query);
        LOGGER.info("Found images: " + images);
        KeyValueEntity<String> imageEntity = KeyValueEntity.of(GALLERY, images);
        bucketManager.put(imageEntity);
        LOGGER.info("Saved in Key-value storage");

    }

    public List<Image> getImages() {
        Optional<Value> gallery = bucketManager.get(GALLERY);
        return gallery.map(value -> value.getList(Image.class)).orElse(Collections.emptyList());
    }

    private ColumnQuery getFindByIdQuery() {
        ColumnQuery query = ColumnQuery.of(GALLERY);
        return query.addCondition(ColumnCondition.eq(Column.of("id", Gallery.DEFAULT_ID)));
    }

}
