package se.ifmo.ru.web.impl;

import jakarta.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import se.ifmo.ru.mapper.FlatMapper;
import se.ifmo.ru.service.api.FlatService;
import se.ifmo.ru.service.model.Flat;
import se.ifmo.ru.service.model.Page;
import se.ifmo.ru.web.api.FlatsWebService;
import se.ifmo.ru.web.exception.NotFoundException;
import se.ifmo.ru.web.exception.VerificationException;
import se.ifmo.ru.web.model.FlatAddOrUpdateRequestDto;
import se.ifmo.ru.web.model.FlatGetResponseDto;
import se.ifmo.ru.web.model.FlatsGetListRequestDto;
import se.ifmo.ru.web.model.FlatsGetListResponseDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.jws.Oneway;
import javax.jws.WebService;

@WebService(endpointInterface = "se.ifmo.ru.web.api.FlatsWebService", serviceName = "flats")
@ApplicationScoped
public class FlatsWebServiceImpl implements FlatsWebService {

    @PostConstruct
    void init(){
        flatService = CDI.current().select(FlatService.class).get();
        flatMapper = CDI.current().select(FlatMapper.class).get();
    }

    private FlatService flatService;

    private FlatMapper flatMapper;

    @Override
    public FlatsGetListResponseDto getFlats(FlatsGetListRequestDto requestDto) {
        Integer page = null, pageSize = null;

        try {
            if (StringUtils.isNotEmpty(requestDto.getPage())) {
                page = Integer.parseInt(requestDto.getPage());
                if (page <= 0) {
                    page = null;
                }
            }
            if (StringUtils.isNotEmpty(requestDto.getSize())) {
                pageSize = Integer.parseInt(requestDto.getSize());
                if (pageSize <= 0) {
                    pageSize = null;
                }
            }
        } catch (NumberFormatException numberFormatException) {
            throw new VerificationException("Invalid query param value");
        }

        Page<Flat> resultPage = flatService.getFlats(
                requestDto.getSort(),
                requestDto.getFilter(),
                page,
                pageSize
        );

        return new FlatsGetListResponseDto(
                flatMapper.toGetResponseDtoList(resultPage.getObjects()),
                resultPage.getPage(),
                resultPage.getPageSize(),
                resultPage.getTotalPages(),
                resultPage.getTotalCount()
        );
    }

    @Override
    public FlatGetResponseDto getFlat(long id) {
        Flat flat = flatService.getFlat(id);

        if (flat == null) {
            throw new NotFoundException("Flat with id " + id + " not found");
        }
        return flatMapper.toDto(flat);
    }

    @Override
    public FlatGetResponseDto addFlat(FlatAddOrUpdateRequestDto requestDto) throws VerificationException {
        validateFlatAddOrUpdateRequestDto(requestDto);

        Flat flat = flatService.addFlat(requestDto);

        return flatMapper.toDto(flat);
    }

    @Override
    public FlatGetResponseDto updateFlat(long id, FlatAddOrUpdateRequestDto requestDto) throws VerificationException {
        validateFlatAddOrUpdateRequestDto(requestDto);

        Flat flat = flatService.updateFlat(id, requestDto);

        if (flat == null) {
            throw new NotFoundException("Flat with id " + id + " not found");
        }

        return flatMapper.toDto(flat);
    }

    @Override
    @Oneway
    public void deleteFlat(long id) {
        boolean deleted = flatService.deleteFlat(id);

        if (!deleted) {
            throw new NotFoundException("Flat with id " + id + " not found");
        }
    }

    @Override
    public long sumHeight() {
/*        return Response
                .ok()
                .entity(SumHeightResponseDto.builder().sum(flatService.sumHeight()).build())
                .build();*/

        return flatService.sumHeight();
    }

    @Override
    public FlatGetResponseDto getWithMaxId() throws NotFoundException {
        Flat flat = flatService.getWithMaxId();

        if (flat == null) {
            throw new NotFoundException("No flats");
        }

        return flatMapper.toDto(flat);
    }

    @Override
    public long countByNew(Boolean value) {
/*        return Response
                .ok()
                .entity(NewCountResponseDto.builder().count(flatService.countByNew(value)).build())
                .build();*/

        return flatService.countByNew(value);
    }

    private void validateFlatAddOrUpdateRequestDto(FlatAddOrUpdateRequestDto requestDto) throws VerificationException {
        if (StringUtils.isEmpty(requestDto.getName())) {
            throw new VerificationException("Name can not be empty");
        }
        if (requestDto.getCoordinates() == null) {
            throw new VerificationException("Coordinates can not be null");
        }
        if (requestDto.getArea() == null || requestDto.getArea() <= 0) {
            throw new VerificationException("Area must be grater than 0");
        }
        if (requestDto.getNumberOfRooms() == null || requestDto.getNumberOfRooms() <= 0) {
            throw new VerificationException("Number of rooms must be greater than 0");
        }
        if (requestDto.getHeight() != null && requestDto.getHeight() <= 0) {
            throw new VerificationException("Height must be greater than 0");
        }
        if (requestDto.getNewField() == null) {
            throw new VerificationException("New can not be null");
        }
        if (StringUtils.isEmpty(requestDto.getTransport())) {
            throw new VerificationException("Transport can not be empty");
        }
        if (requestDto.getHouse() == null) {
            throw new VerificationException("House can not be null");
        }
        if (requestDto.getCoordinates() == null) {
            throw new VerificationException("Coordinates can not be null");
        }
        if (requestDto.getCoordinates().getX() == null || requestDto.getCoordinates().getX() <= -292) {
            throw new VerificationException("Coordinates X must be greater than -292");
        }
        if (requestDto.getCoordinates().getY() == null || requestDto.getCoordinates().getY() <= -747) {
            throw new VerificationException("Coordinates Y must be greater than -747");
        }
        if (requestDto.getHouse().getName() == null) {
            throw new VerificationException("House name can not be null");
        }
        if (requestDto.getHouse().getYear() != null && requestDto.getHouse().getYear() <= 0) {
            throw new VerificationException("House year m,ust be greater than 0");
        }
        if (requestDto.getHouse().getNumberOfFloors() == null || requestDto.getHouse().getNumberOfFloors() <= 0) {
            throw new VerificationException("House number of floors must be greater than 0");
        }
        if (requestDto.getHouse().getNumberOfLifts() != null && requestDto.getHouse().getNumberOfLifts() != null && requestDto.getHouse().getNumberOfLifts() <= 0) {
            throw new VerificationException("House number of lifts must be greater than 0");
        }
    }
}
