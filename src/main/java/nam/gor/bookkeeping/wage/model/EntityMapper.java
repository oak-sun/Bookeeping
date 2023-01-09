package nam.gor.bookkeeping.wage.model;

import nam.gor.bookkeeping.wage.model.dto.OfficeDto;
import nam.gor.bookkeeping.wage.model.dto.RegionDto;
import nam.gor.bookkeeping.wage.model.dto.BonusDto;
import nam.gor.bookkeeping.wage.model.dto.WageDto;
import nam.gor.bookkeeping.wage.model.entity.Office;
import nam.gor.bookkeeping.wage.model.entity.Region;
import nam.gor.bookkeeping.wage.model.entity.Bonus;
import nam.gor.bookkeeping.wage.model.entity.Wage;
import org.springframework.beans.BeanUtils;

public class EntityMapper {
    public static RegionDto convertToDTO(Region region) {
        var dto = new RegionDto();
        BeanUtils.copyProperties(region, dto);
        return dto;
    }

    public static Region convertToEntity(RegionDto dto) {
        var region = new Region();
        BeanUtils.copyProperties(dto, region);
        return region;
    }

    public static OfficeDto convertToDTO(Office office) {
        var dto = new OfficeDto();
        BeanUtils.copyProperties(office, dto);
        return dto;
    }

    public static Office convertToEntity(OfficeDto dto) {
        var office = new Office();
        BeanUtils.copyProperties(dto, office);
        return office;
    }

    public static Bonus convertToEntity(BonusDto dto) {
        return new Bonus(dto.getId(),
                            dto.getName());
    }

    public static BonusDto convertToDTO(Bonus bonus) {
        return new BonusDto(bonus.getId(), bonus.getName());
    }

    public static WageDto convertToDTO(Wage wage) {
        var dto = new WageDto();
        BeanUtils.copyProperties(wage, dto);
        return dto;
    }

    public static Wage convertToEntity(WageDto dto) {
        var wage = new Wage();
        BeanUtils.copyProperties(dto, wage);
        return wage;
    }
}
