package se.ifmo.ru.web.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@XmlType
@XmlRootElement(name = "Flats")
public class FlatsGetListResponseDto {
    private List<FlatGetResponseDto> flatGetResponseDtos;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalCount;

    @XmlElement(name = "Flat")
    public List<FlatGetResponseDto> getFlatGetResponseDtos() {
        return flatGetResponseDtos;
    }

    public FlatsGetListResponseDto(List<FlatGetResponseDto> flatGetResponseDtos, Integer page, Integer pageSize, Integer totalPages, Long totalCount) {
        this.flatGetResponseDtos = flatGetResponseDtos;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }
}
