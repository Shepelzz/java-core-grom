package lesson36.service;

import lesson36.dao.HotelDAO;
import lesson36.dao.RoomDAO;
import lesson36.exception.DAOException;
import lesson36.exception.ServiceException;
import lesson36.model.Filter;
import lesson36.model.Room;

import java.util.Date;
import java.util.Set;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();

    //ADMIN
    public Room addRoom(Room room) throws DAOException, ServiceException {
        validate(room);
        return roomDAO.addRoom(room);
    }

    //ADMIN
    public void deleteRoom(long roomId) throws DAOException{
        roomDAO.deleteRoom(roomId);
    }

    public Set<Room> findRooms(Filter filter) throws DAOException, ServiceException{
        if(filter.getDateAvailableFrom().before(new Date()))
            throw new ServiceException("Room searching error: date can not be arlier than current");

        return roomDAO.findRooms(filter);
    }

    private void validate(Room room) throws ServiceException{
        if(room.getNumberOfGuests() <= 0)
            throw new ServiceException("Room adding error: numberOfGuests can not be less than 1");
        if(room.getPrice() <= 0)
            throw new ServiceException("Room adding error: price can not be negative or zero");
        if(room.getDateAvailableFrom() == null)
            throw new ServiceException("Room adding error: price can not be negative or zero");
    }
}