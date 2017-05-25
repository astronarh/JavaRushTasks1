package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery
{

    private Path logDir;
    private List<String> linesList;

    public LogParser(Path logDir)
    {
        this.logDir = logDir;
        linesList = getLinesList();
    }

    private List<String> getLinesList()
    {
        String[] files = logDir.toFile().list(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".log");
            }
        });

        List<String> lines = new ArrayList<>();
        for (String file : files)
        {
            try
            {
                lines.addAll(Files.readAllLines(Paths.get(logDir + File.separator + file), Charset.defaultCharset()));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return lines;
    }

    private void addStringEntity(Date after, Date before, Set<String> enteties, String[] parts, int part)
    {
        long lineDateTime = getDate(parts[2]).getTime();

        if (isCompatibleDate(lineDateTime, after, before))
        {
            enteties.add(parts[part]);
        }
    }

    private void addDateEntity(Date after, Date before, Set<Date> enteties, String[] parts)
    {
        Date lineDate = getDate(parts[2]);
        long lineDateTime = getDate(parts[2]).getTime();

        if (isCompatibleDate(lineDateTime, after, before))
        {
            enteties.add(lineDate);
        }
    }

    private void addEventEntity(Date after, Date before, Set<Event> enteties, String[] parts)
    {
        Event lineEvent = Event.valueOf(parts[3].split(" ")[0]);
        long lineDateTime = getDate(parts[2]).getTime();

        if (isCompatibleDate(lineDateTime, after, before))
        {
            enteties.add(lineEvent);
        }
    }

    private boolean isCompatibleDate(long lineDateTime, Date after, Date before)
    {
        if (after == null && before == null)
        {
            return true;
        } else if (after == null)
        {
            if (lineDateTime <= before.getTime())
            {
                return true;
            }
        } else if (before == null)
        {
            if (lineDateTime >= after.getTime())
            {
                return true;
            }
        } else
        {
            if (lineDateTime >= after.getTime() && lineDateTime <= before.getTime())
            {
                return true;
            }
        }
        return false;
    }

    private Date getDate(String part)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        Date date = null;
        try
        {
            date = dateFormat.parse(part);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before)
    {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before)
    {
        Set<String> uniqueIPs = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");

            addStringEntity(after, before, uniqueIPs, parts, 0);
        }
        return uniqueIPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before)
    {
        Set<String> IPsForUser = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");

            if (user.equals(parts[1]))
            {
                addStringEntity(after, before, IPsForUser, parts, 0);
            }
        }
        return IPsForUser;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before)
    {
        Set<String> IPsForEvent = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");

            if (event.toString().equals(parts[3].split(" ")[0]))
            {
                addStringEntity(after, before, IPsForEvent, parts, 0);
            }
        }
        return IPsForEvent;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before)
    {
        Set<String> IPsForEvent = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");

            if (status.toString().equals(parts[4]))
            {
                addStringEntity(after, before, IPsForEvent, parts, 0);
            }
        }
        return IPsForEvent;
    }

    @Override
    public Set<String> getAllUsers()
    {
        Set<String> allUsers = new HashSet<>();

        for (String line : linesList)
        {
            allUsers.add(line.split("\\t")[1]);
        }
        return allUsers;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (String line : linesList)
        {
            String[] parts = line.split("\\t");
            addStringEntity(after, before, users, parts, 1);
        }
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before)
    {
        Set<String> userEvents = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");

            if (user.equals(parts[1]))
            {
                addStringEntity(after, before, userEvents, parts, 3);
            }
        }
        return userEvents.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before)
    {
        Set<String> usersForIP = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");

            if (ip.equals(parts[0]))
            {
                addStringEntity(after, before, usersForIP, parts, 1);
            }
        }
        return usersForIP;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before)
    {
        Set<String> loggedUsers = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");

            if (Event.LOGIN.toString().equals(parts[3]))
            {
                addStringEntity(after, before, loggedUsers, parts, 1);
            }
        }
        return loggedUsers;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before)
    {
        Set<String> downloadedPluginUsers = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");

            if (Event.DOWNLOAD_PLUGIN.toString().equals(parts[3]))
            {
                addStringEntity(after, before, downloadedPluginUsers, parts, 1);
            }
        }
        return downloadedPluginUsers;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before)
    {
        Set<String> wroteMessageUsers = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");

            if (Event.WRITE_MESSAGE.toString().equals(parts[3]))
            {
                addStringEntity(after, before, wroteMessageUsers, parts, 1);
            }
        }
        return wroteMessageUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before)
    {
        Set<String> solvedTaskUsers = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");

            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0]))
            {
                addStringEntity(after, before, solvedTaskUsers, parts, 1);
            }
        }
        return solvedTaskUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task)
    {
        Set<String> solvedTaskUsers = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");
            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0])
                    && task == Integer.valueOf(parts[3].split(" ")[1]))
            {
                addStringEntity(after, before, solvedTaskUsers, parts, 1);
            }
        }
        return solvedTaskUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before)
    {
        Set<String> doneTaskUsers = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");

            if (Event.DONE_TASK.toString().equals(parts[3].split(" ")[0]))
            {
                addStringEntity(after, before, doneTaskUsers, parts, 1);
            }
        }
        return doneTaskUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task)
    {
        Set<String> doneTaskUsers = new HashSet<>();

        for (String line : linesList)
        {
            String[] parts = line.split("\\t");
            if (Event.DONE_TASK.toString().equals(parts[3].split(" ")[0])
                    && task == Integer.valueOf(parts[3].split(" ")[1]))
            {
                addStringEntity(after, before, doneTaskUsers, parts, 1);
            }
        }
        return doneTaskUsers;
    }
}