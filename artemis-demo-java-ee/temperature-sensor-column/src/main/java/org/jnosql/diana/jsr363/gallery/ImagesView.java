package org.jnosql.diana.jsr363.gallery;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ApplicationScoped
@Named
public class ImagesView {

    @Inject
    private ImageRepository imageRepository;


    public List<Image> getImages() {
        return imageRepository.getImages();
    }
}